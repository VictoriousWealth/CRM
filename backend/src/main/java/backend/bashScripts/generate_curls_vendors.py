#!/usr/bin/env python3
import random
import string

# File to store curl commands
output_file = "register_vendors.sh"

# 30 predefined vendors (custom details)
predefined_vendors = [
    {"username": "vendor_apple", "password": "securepass1", "businessName": "Apple Store", "businessAddress": "1 Infinite Loop, CA"},
    {"username": "vendor_nike", "password": "securepass2", "businessName": "Nike Outlet", "businessAddress": "Beaverton, Oregon"},
    {"username": "vendor_tesla", "password": "securepass3", "businessName": "Tesla Dealership", "businessAddress": "Palo Alto, CA"},
    {"username": "vendor_samsung", "password": "securepass4", "businessName": "Samsung Hub", "businessAddress": "Seoul, South Korea"},
    {"username": "vendor_amazon", "password": "securepass5", "businessName": "Amazon HQ", "businessAddress": "Seattle, WA"},
    {"username": "vendor_microsoft", "password": "securepass6", "businessName": "Microsoft Store", "businessAddress": "Redmond, WA"},
    {"username": "vendor_sony", "password": "securepass7", "businessName": "Sony Electronics", "businessAddress": "Tokyo, Japan"},
    {"username": "vendor_adidas", "password": "securepass8", "businessName": "Adidas Sports", "businessAddress": "Herzogenaurach, Germany"},
    {"username": "vendor_puma", "password": "securepass9", "businessName": "Puma Wear", "businessAddress": "Herzogenaurach, Germany"},
    {"username": "vendor_rolex", "password": "securepass10", "businessName": "Rolex Watches", "businessAddress": "Geneva, Switzerland"},
    {"username": "vendor_gucci", "password": "securepass11", "businessName": "Gucci Fashion", "businessAddress": "Florence, Italy"},
    {"username": "vendor_zara", "password": "securepass12", "businessName": "Zara Clothing", "businessAddress": "Arteixo, Spain"},
    {"username": "vendor_ikea", "password": "securepass13", "businessName": "IKEA Furniture", "businessAddress": "Delft, Netherlands"},
    {"username": "vendor_levis", "password": "securepass14", "businessName": "Levi'\\''s Jeans", "businessAddress": "San Francisco, CA"},
    {"username": "vendor_bose", "password": "securepass15", "businessName": "Bose Audio", "businessAddress": "Framingham, MA"},
    {"username": "vendor_logitech", "password": "securepass16", "businessName": "Logitech Tech", "businessAddress": "Lausanne, Switzerland"},
    {"username": "vendor_coca", "password": "securepass17", "businessName": "Coca-Cola", "businessAddress": "Atlanta, GA"},
    {"username": "vendor_pepsi", "password": "securepass18", "businessName": "PepsiCo", "businessAddress": "Purchase, NY"},
    {"username": "vendor_hp", "password": "securepass19", "businessName": "HP Computers", "businessAddress": "Palo Alto, CA"},
    {"username": "vendor_dell", "password": "securepass20", "businessName": "Dell Technologies", "businessAddress": "Round Rock, TX"},
    {"username": "vendor_huawei", "password": "securepass21", "businessName": "Huawei Technologies", "businessAddress": "Shenzhen, China"},
    {"username": "vendor_lg", "password": "securepass22", "businessName": "LG Electronics", "businessAddress": "Seoul, South Korea"},
    {"username": "vendor_panasonic", "password": "securepass23", "businessName": "Panasonic", "businessAddress": "Osaka, Japan"},
    {"username": "vendor_honda", "password": "securepass24", "businessName": "Honda Motors", "businessAddress": "Tokyo, Japan"},
    {"username": "vendor_toyota", "password": "securepass25", "businessName": "Toyota Dealership", "businessAddress": "Aichi, Japan"},
    {"username": "vendor_mercedes", "password": "securepass26", "businessName": "Mercedes-Benz", "businessAddress": "Stuttgart, Germany"},
    {"username": "vendor_bmw", "password": "securepass27", "businessName": "BMW Auto", "businessAddress": "Munich, Germany"},
    {"username": "vendor_audi", "password": "securepass28", "businessName": "Audi Cars", "businessAddress": "Ingolstadt, Germany"},
    {"username": "vendor_volkswagen", "password": "securepass29", "businessName": "Volkswagen", "businessAddress": "Wolfsburg, Germany"},
    {"username": "vendor_ford", "password": "securepass30", "businessName": "Ford Motors", "businessAddress": "Dearborn, MI"}
]

def generate_random_string(length=8):
    """Generate a random string of fixed length."""
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for _ in range(length))

def generate_random_vendor(index):
    """Generate random vendor details."""
    username = f"vendor_{index}"
    password = generate_random_string(10)
    business_name = f"Business_{index}"
    business_address = f"{random.randint(1, 9999)} {generate_random_string(7)} Street"

    return {
        "username": username,
        "password": password,
        "businessName": business_name,
        "businessAddress": business_address
    }

with open(output_file, "w") as f:
    f.write("#!/bin/bash\n\n")  # Add bash script header

    # Write predefined vendors
    f.writelines([f'curl -X POST http://localhost:9000/register/vendor -H "Content-Type: application/json" -d \'{{"username": "{vendor["username"]}", "password": "{vendor["password"]}", "businessName": "{vendor["businessName"]}", "businessAddress": "{vendor["businessAddress"]}"}}\'\n' for vendor in predefined_vendors])

    # Generate and write 170 random vendors
    f.writelines([f'curl -X POST http://localhost:9000/register/vendor -H "Content-Type: application/json" -d \'{{"username": "{vendor["username"]}", "password": "{vendor["password"]}", "businessName": "{vendor["businessName"]}", "businessAddress": "{vendor["businessAddress"]}"}}\'\n' for vendor in (generate_random_vendor(i) for i in range(31, 201))])


print(f" File '{output_file}' has been created with 200 vendor registration cURL commands!")
