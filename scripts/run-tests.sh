#!/bin/bash

# Function to parse the instrument options into an array
parse_instrument_options() {
    local options="$1"  # The value of --instrument-options
    IFS=', ' read -ra param_array <<< "$options"
    echo "${param_array[@]}"
}

# Function to construct the multiple_tests variable
construct_multiple_tests() {
    local param_array=("$@")
    local multiple_tests=""
    for param in "${param_array[@]}"; do
        # Construct the format for each parameter
        multiple_tests+="com.abc.*.$param, "
    done
    # Remove the trailing comma and space
    multiple_tests="${multiple_tests%, }"
    echo "$multiple_tests"
}

# Check if the correct number of arguments is provided
if [ $# -ne 1 ]; then
    echo "Usage: $0 --instrument-options=<param A, param B, param C>"
    exit 1
fi

# Extract the value of --instrument-options
while [[ "$1" ]]; do
    case "$1" in
        --instrument-options=*)
            instrument_options="${1#*=}"
            ;;
        *)
            echo "Invalid argument: $1"
            exit 1
            ;;
    esac
    shift
done

# Parse the instrument options into an array
param_array=($(parse_instrument_options "$instrument_options"))

# Construct the multiple_tests variable
multiple_tests=$(construct_multiple_tests "${param_array[@]}")

# Now you can use the multiple_tests variable in gradlew
echo "Multiple tests: $multiple_tests"
./gradlew app:connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class="$multiple_tests"

