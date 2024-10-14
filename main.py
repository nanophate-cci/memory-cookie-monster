import sys
import time

def allocate_memory(target_gb, hold_time_minutes):
    try:
        print(f"Starting memory allocation: {target_gb} GB")
        # Each float in Python roughly takes 24 bytes
        # Calculate the number of floats needed to allocate target_gb
        num_floats = (target_gb * 1024 ** 3) // 24
        print(f"Allocating {target_gb} GB of memory by creating a list with {num_floats} floats.")
        memory_holder = [0.0] * num_floats
        print(f"Successfully allocated {target_gb} GB of memory.")
        
        print(f"Holding allocated memory for {hold_time_minutes} minutes...")
        time.sleep(hold_time_minutes * 60)
        
        print("Releasing memory.")
        del memory_holder
        print("Memory released successfully.")
        
    except MemoryError:
        print("Memory allocation failed! Not enough memory available.")
        sys.exit(1)
    except Exception as e:
        print(f"An unexpected error occurred: {e}")
        sys.exit(1)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python3 memory_alloc.py <target_gb> <hold_time_minutes>")
        sys.exit(1)
    
    try:
        target_gb = int(sys.argv[1])
        hold_time_minutes = int(sys.argv[2])
    except ValueError:
        print("Both <target_gb> and <hold_time_minutes> must be integers.")
        sys.exit(1)
    
    allocate_memory(target_gb, hold_time_minutes)
