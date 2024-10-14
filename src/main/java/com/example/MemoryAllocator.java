package com.example;

public class MemoryAllocator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MemoryAllocator <memory_in_GB> <hold_time_in_minutes>");
            System.exit(1);
        }

        long memoryInGB;
        int holdTimeInMinutes;

        try {
            memoryInGB = Long.parseLong(args[0]);
            holdTimeInMinutes = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Both <memory_in_GB> and <hold_time_in_minutes> must be valid integers.");
            System.exit(1);
            return;
        }

        try {
            System.out.printf("Attempting to allocate %d GB of memory...%n", memoryInGB);
            // Allocate memory by creating a large byte array
            byte[] memory = new byte[(int) (memoryInGB * 1024 * 1024 * 1024L)];
            System.out.printf("Successfully allocated %d GB of memory.%n", memoryInGB);

            System.out.printf("Holding memory for %d minutes...%n", holdTimeInMinutes);
            Thread.sleep(holdTimeInMinutes * 60 * 1000L);

            System.out.println("Releasing memory.");
            // Help garbage collector by nullifying the array reference
            memory = null;
            System.gc();
            System.out.println("Memory released successfully.");

        } catch (OutOfMemoryError e) {
            System.out.println("Failed to allocate memory: " + e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Execution interrupted: " + e.getMessage());
            System.exit(1);
        }
    }
}
