#!/bin/bash

# Java JUnit 5 Autograder for PrairieLearn
# This script compiles and runs JUnit tests for submitted Java code

set -e

echo "Starting Java autograder..."

# Create results directory
mkdir -p /grade/results

# Change to grade directory
cd /grade

# Initialize results
cat > /grade/results/results.json << 'EOF'
{
    "gradable": true,
    "score": 0.0,
    "message": "Starting Java compilation and testing...",
    "output": "",
    "tests": []
}
EOF

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
    "format_errors": ["$error_msg"],
    "tests": []
}
EOF
}

# Function to extract points from @Tag annotation
extract_points() {
    local test_file="$1"
    local test_method="$2"
    # Look for @Tag("points=X") annotation for the specific test method
    local points=$(grep -A 10 -B 2 "public void $test_method" "$test_file" | grep -o '@Tag("points=[0-9]*")' | head -1 | grep -o '[0-9]*')
    echo "${points:-1}"
}

# Check if student submitted any files
if [ ! -d "/grade/student" ] || [ -z "$(ls -A /grade/student 2>/dev/null)" ]; then
    update_error_results "No files submitted" "Please submit your Java files for grading."
    exit 0
fi

# Find Java files in student directory
java_files=$(find /grade/student -name "*.java" | head -10)
if [ -z "$java_files" ]; then
    update_error_results "No Java files found" "Please submit .java files for grading."
    exit 0
fi

echo "Found Java files:"
echo "$java_files"

# Copy student files to working directory
cp -r /grade/student/* .

# Copy test files to working directory
if [ -d "/grade/tests" ]; then
    cp -r /grade/tests/* .
fi

# Try to compile student code first
echo "Compiling student code..."
compilation_output=""
if ! compilation_output=$(javac -cp ".:junit-platform-console-standalone.jar" *.java 2>&1); then
    echo "Compilation failed:"
    echo "$compilation_output"
    update_error_results "Compilation failed" "$compilation_output"
    exit 0
fi

echo "Compilation successful!"

# Find test classes
test_classes=$(find . -name "*Test.java" -o -name "Test*.java" | sed 's|^\./||' | sed 's|\.java$||' | tr '/' '.')

if [ -z "$test_classes" ]; then
    update_error_results "No test files found" "Test files are required for grading."
    exit 0
fi

echo "Found test classes: $test_classes"

# Run tests and capture results
total_score=0
max_total_score=0
test_results="[]"
output_log=""

for test_class in $test_classes; do
    echo "Running tests in $test_class..."
    
    # Run JUnit tests with detailed output
    test_output=$(java -cp ".:junit-platform-console-standalone.jar" \
        org.junit.platform.console.ConsoleLauncher \
        --class-path=. \
        --select-class="$test_class" \
        --details=verbose 2>&1 || true)
    
    output_log="$output_log\n=== Testing $test_class ===\n$test_output\n"
    
    # Parse test results
    # Extract test method names and their status
    while IFS= read -r line; do
        if echo "$line" | grep -q "✓\|✗"; then
            # Extract test name
            test_name=$(echo "$line" | sed -n 's/.*\[\([^]]*\)\].*/\1/p' | sed 's/()$//')
            if [ -n "$test_name" ]; then
                # Find corresponding Java test file
                test_file=$(find . -name "*Test.java" -exec grep -l "$test_name" {} \;)
                
                # Extract points for this test
                points=$(extract_points "$test_file" "$test_name")
                max_total_score=$((max_total_score + points))
                
                # Check if test passed
                if echo "$line" | grep -q "✓"; then
                    total_score=$((total_score + points))
                    status="passed"
                    message="Test passed successfully"
                else
                    status="failed"
                    message="Test failed"
                fi
                
                # Build test result JSON
                test_result=$(cat << EOF
{
    "name": "$test_name",
    "description": "$test_name",
    "points": $points,
    "max_points": $points,
    "message": "$message",
    "output": "$(echo "$test_output" | grep -A 5 -B 5 "$test_name" | head -10 | sed 's/"/\\"/g')"
}
EOF
)
                
                # Add to test results array
                if [ "$test_results" = "[]" ]; then
                    test_results="[$test_result]"
                else
                    test_results=$(echo "$test_results" | sed 's/]$/,'"$test_result"']/')
                fi
            fi
        fi
    done <<< "$test_output"
done

# Calculate final score as percentage
if [ "$max_total_score" -gt 0 ]; then
    final_score=$(echo "scale=3; $total_score / $max_total_score" | bc)
else
    final_score="0.0"
fi

echo "Tests completed. Score: $total_score/$max_total_score ($final_score)"

# Create final results JSON
cat > /grade/results/results.json << EOF
{
    "gradable": true,
    "score": $final_score,
    "message": "Testing completed. Score: $total_score/$max_total_score points",
    "output": "$(echo -e "$output_log" | sed 's/"/\\"/g' | tr '\n' '\\n')",
    "tests": $test_results
}
EOF

echo "Grading complete!"