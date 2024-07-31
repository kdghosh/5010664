package BuilderPatternExample;

public class Computer 
{
    // Attributes of the Computer
    private String CPU;
    private String RAM;
    private String storage;
    private String graphicsCard;
    private String operatingSystem;
    private boolean isBluetoothEnabled;
    private boolean isWiFiEnabled;

    // Private constructor to ensure objects are created only through the Builder
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.operatingSystem = builder.operatingSystem;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
        this.isWiFiEnabled = builder.isWiFiEnabled;
    }

    // Getters for the attributes
    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getStorage() {
        return storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    public boolean isWiFiEnabled() {
        return isWiFiEnabled;
    }

    // Static nested Builder class
    public static class Builder {
        // Required parameters
        private String CPU;
        private String RAM;

        // Optional parameters - initialized to default values
        private String storage = "256GB";
        private String graphicsCard = "Integrated";
        private String operatingSystem = "Windows 10";
        private boolean isBluetoothEnabled = false;
        private boolean isWiFiEnabled = false;

        // Builder constructor with required parameters
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Methods to set optional parameters
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Builder setWiFiEnabled(boolean isWiFiEnabled) {
            this.isWiFiEnabled = isWiFiEnabled;
            return this;
        }

        // Method to build and return an instance of Computer
        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage + 
               ", graphicsCard=" + graphicsCard + ", operatingSystem=" + operatingSystem + 
               ", isBluetoothEnabled=" + isBluetoothEnabled + ", isWiFiEnabled=" + isWiFiEnabled + "]";
    }
    public static void main(String[] args) {
        // Create a Computer object using the Builder pattern
        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 3080")
                .setOperatingSystem("Windows 11")
                .setBluetoothEnabled(true)
                .setWiFiEnabled(true)
                .build();

        System.out.println(gamingComputer);

        // Create another Computer object with different configuration
        Computer officeComputer = new Computer.Builder("Intel i5", "16GB")
                .setStorage("512GB SSD")
                .setGraphicsCard("Integrated")
                .setOperatingSystem("Windows 10")
                .build();

        System.out.println(officeComputer);
    }
}
