#!/bin/bash

# Check if environment variables are set
if [ -z "$GOOGLE_CLIENT_ID" ] || [ -z "$GOOGLE_CLIENT_SECRET" ]; then
    echo "Error: GOOGLE_CLIENT_ID and GOOGLE_CLIENT_SECRET environment variables must be set"
    exit 1
fi

# Generate config.json from template by replacing placeholders
sed "s/GOOGLE_CLIENT_ID_PLACEHOLDER/$GOOGLE_CLIENT_ID/g" config-template.json | \
sed "s/GOOGLE_CLIENT_SECRET_PLACEHOLDER/$GOOGLE_CLIENT_SECRET/g" > config.json

echo "Generated config.json with OAuth credentials from environment variables"