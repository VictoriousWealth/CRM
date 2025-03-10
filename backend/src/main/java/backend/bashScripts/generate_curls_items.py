#!/usr/bin/env python3
import random
import string

USERNAME = "vendor"
PASSWORD = "vendor"

# List of vendor IDs
vendor_ids = [
    35, 37, 39, 41, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 65, 66, 67, 68,
    69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88,
    89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106,
    107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122,
    123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138,
    139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154,
    155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170,
    171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186,
    187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202,
    203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218,
    219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234,
    235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 249
]

# Generate random string
def random_string(length=10):
    return ''.join(random.choices(string.ascii_letters, k=length))

# Generate a random item
def generate_random_item():
    return {
        "name": random_string(8),
        "description": random_string(20),
        "stockCount": random.randint(1, 500),
        "vendorId": random.choice(vendor_ids)
    }

# Generate items and distribute across vendors
items_per_vendor = {vendor_id: 0 for vendor_id in vendor_ids}
items = []

for _ in range(10000):
    item = generate_random_item()

    # Distribute items more evenly
    min_vendor = min(items_per_vendor, key=items_per_vendor.get)
    item["vendorId"] = min_vendor
    items_per_vendor[min_vendor] += 1

    items.append(item)

# Write the `curl` commands to a shell script
with open("register_items.sh", "w") as f:
    f.write("#!/bin/bash\n\n")
    for item in items:
        f.write(f'curl -X POST http://localhost:9000/vendor/item -u "{USERNAME}:{PASSWORD}" -H "Content-Type: application/json" -d \'{{"name": "{item["name"]}", "description": "{item["description"]}", "stockCount": {item["stockCount"]}, "vendorId": {item["vendorId"]} }}\'\n')

print("Shell script 'register_items.sh' generated successfully!")
