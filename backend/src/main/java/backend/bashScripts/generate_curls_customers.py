#!/usr/bin/env python3
import random
import string

def generate_random_string(length=8):
    """Generate a random string of fixed length."""
    return ''.join(random.choices(string.ascii_lowercase, k=length))

def generate_random_name():
    """Generate a random first, middle, or last name (all lowercase)."""
    return generate_random_string(random.randint(5, 10))

def generate_curl_command(index):
    """Generate a cURL command with unique user details."""
    username = f"user_{index}"
    password = generate_random_string(10)
    first_name = generate_random_name()
    middle_name = generate_random_name()
    last_name = generate_random_name()

    curl_command = f"""curl -X POST http://localhost:9000/register/customer \\
-H "Content-Type: application/json" \\
-d '{{"username": "{username}", "password": "{password}", "firstName": "{first_name}", "middleName": "{middle_name}", "lastName": "{last_name}"}}'"""

    return curl_command

def main():
    start = 1
    num_commands = start+(10_000-48) #how many more do u need
    output_file = "register_customers.sh"

    with open(output_file, "w") as f:
        f.write("#!/bin/bash\n\n")
        for i in range(start, num_commands + 1):
            f.write(generate_curl_command(i) + "\n")

    print(f"{num_commands-start} cURL commands saved to {output_file}")

if __name__ == "__main__":
    main()
