FROM prairielearn/prairielearn

# Copy course files
COPY courses/javaOOPCourse /PrairieLearn/courses/javaOOPCourse

# Copy config
COPY config.json /PrairieLearn/config.json

# Set working directory
WORKDIR /PrairieLearn

# Expose port 8080 for Cloud Run
EXPOSE 8080

# Set PORT environment variable for Cloud Run
ENV PORT=8080

# Start the server with the Cloud Run port
CMD ["node", "server.js", "--port", "8080"]