#!/bin/bash

# Simple Java JUnit 5 Autograder for PrairieLearn
# Uses the prairielearn/grader-java image built-in tools

set -e

echo "Starting Java autograder (simple version)..."

# Create results directory
mkdir -p /grade/results

# Change to grade directory
cd /grade

# Function to update results with error
update_error_results() {
    local error_msg="$1"
    local output_msg="$2"
    cat > /grade/results/results.json << EOF
{
    "gradable": false,
    "score": 0.0,
    "message": "$error_msg",
    "output": "$output_msg",
    "format_errors": ["$error_msg"]
}
EOF
}

# Check if student submitted any files
if [ ! -d "/grade/student" ] || [ -z "$(ls -A /grade/student 2>/dev/null)" ]; then
    update_error_results "No files submitted" "Please submit your Java files for grading."
    exit 0
fi

# Find Java files in student directory
java_files=$(find /grade/student -name "*.java")
if [ -z "$java_files" ]; then
    update_error_results "No Java files found" "Please submit .java files for grading."
    exit 0
fi

echo "Found Java files: $java_files"

# Copy all files to working directory
cp -r /grade/student/* . 2>/dev/null || true
if [ -d "/grade/tests" ]; then
    cp -r /grade/tests/* . 2>/dev/null || true
fi

# Compile all Java files
echo "Compiling Java files..."
compilation_output=""
if ! compilation_output=$(javac -cp "/usr/share/java/*:." *.java 2>&1); then
    echo "Compilation failed: $compilation_output"
    update_error_results "Compilation Error" "$compilation_output"
    exit 0
fi

echo "Compilation successful!"

# Find test classes
test_files=$(find . -name "*Test.java" -o -name "Test*.java")
if [ -z "$test_files" ]; then
    update_error_results "No test files found" "Test files (*Test.java) are required for grading."
    exit 0
fi

# Run tests using JUnit 5
echo "Running JUnit tests..."
test_output=""
test_exit_code=0

# Try to use JUnit 5 console launcher if available
if command -v junit5 >/dev/null 2>&1; then
    test_output=$(junit5 --class-path=. --scan-class-path 2>&1) || test_exit_code=$?
elif [ -f "/usr/share/java/junit-platform-console-standalone.jar" ]; then
    test_output=$(java -jar /usr/share/java/junit-platform-console-standalone.jar --class-path=. --scan-class-path 2>&1) || test_exit_code=$?
else
    # Fallback: try to run tests directly
    for test_file in $test_files; do
        test_class=$(basename "$test_file" .java)
        echo "Running $test_class..."
        if java -cp "/usr/share/java/*:." org.junit.platform.console.ConsoleLauncher --select-class="$test_class" 2>&1; then
            echo "✓ $test_class passed"
        else
            echo "✗ $test_class failed"
            test_exit_code=1
        fi
    done
fi

echo "Test output:"
echo "$test_output"

# Calculate score based on test results
# Simple scoring: if no test failures, score = 1.0, otherwise 0.0
if [ $test_exit_code -eq 0 ] && ! echo "$test_output" | grep -q "FAILED\|ERROR\|✗"; then
    score="1.0"
    message="All tests passed!"
else
    score="0.0"
    message="Some tests failed. Please check your implementation."
fi

# Create results JSON
cat > /grade/results/results.json << EOF
{
    "gradable": true,
    "score": $score,
    "message": "$message",
    "output": "$(echo "$test_output" | sed 's/"/\\"/g' | tr '\n' ' ')"
}
EOF

echo "Grading complete! Score: $score"