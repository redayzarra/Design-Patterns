// Create a class to define the object we want to build
class Computer {
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;

    // Getters (which retrieve the computer's details)
    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    // Setters (which set the values during construction)
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "CPU: " + cpu + ", GPU: " + gpu + ", RAM: " + ram + ", Storage: " + storage;
    }
}

// Create an interface for the builder design pattern
interface Builder {
    void addCpu();
    void addGpu();
    void addRam();
    void addStorage();
    Computer build();
}

class ComputerBuilder implements Builder {
    private Computer computer;

    public ComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void addCpu() {
        computer.setCpu("AMD Ryzen 9 5900X");
    }

    @Override
    public void addGpu() {
        computer.setGpu("Nvidia RTX 3080");
    }

    @Override
    public void addRam() {
        computer.setRam("32GB");
    }

    @Override
    public void addStorage() {
        computer.setStorage("1TB NVMe SSD");
    }

    @Override
    public Computer build() {
        return this.computer;
    }
}

// Define the Director class which controls the construction process
class Director {
    public Computer constructComputer(Builder builder) {
        builder.addCpu();
        builder.addGpu();
        builder.addRam();
        builder.addStorage();
        return builder.build();
    }
}

