# FareCalc - Travel Optimizer (Final Version)

# Step 1: Dictionary for vehicle rates (per km) - using lowercase keys
rates = {
    "economy": 10,
    "premium": 18,
    "suv": 25
}

# Step 2: Function to calculate fare
def calculate_fare(km, vehicle_type, hour):
    try:
        # Check if vehicle type exists
        if vehicle_type not in rates:
            return "Service Not Available"

        base_rate = rates[vehicle_type]
        total = km * base_rate

        # Step 3: Apply surge pricing (5 PM - 8 PM)
        if 17 <= hour <= 20:
            total *= 1.5

        return total

    except Exception:
        return "Error occurred"


# Step 4: User Inputs
print("====== CityCab Ride Fare Calculator ======")

try:
    distance = float(input("Enter distance (in km): "))

    # Convert input to lowercase to avoid case issues
    vehicle = input("Enter vehicle type (Economy / Premium / SUV): ").strip().lower()

    hour = int(input("Enter hour of travel (0-23): "))

    # Step 5: Calculate fare
    result = calculate_fare(distance, vehicle, hour)

    # Step 6: Output Receipt
    print("\n====== Ride Receipt ======")

    if result == "Service Not Available":
        print("❌ Service Not Available for selected vehicle.")
    else:
        print(f"Distance Travelled : {distance} km")
        print(f"Vehicle Type       : {vehicle.capitalize()}")
        print(f"Travel Hour        : {hour}")

        if 17 <= hour <= 20:
            print("Surge Applied      : YES (1.5x)")
        else:
            print("Surge Applied      : NO")

        print(f"Total Fare         : ₹ {round(result, 2)}")

    print("===========================")

except ValueError:
    print("❌ Invalid input! Please enter correct values.")