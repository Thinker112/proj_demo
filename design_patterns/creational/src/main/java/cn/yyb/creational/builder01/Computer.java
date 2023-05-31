package cn.yyb.creational.builder01;

public class Computer {
    //required parameters
    private String HDD;
    private String RAM;

    public Computer(String HDD, String RAM) {
        this.HDD = HDD;
        this.RAM = RAM;
    }

    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public static ComputerBuilder builder(){
        return new ComputerBuilder();
    }

    //Builder Class
    public static class ComputerBuilder{

        // required parameters
        private String HDD;
        private String RAM;

        public ComputerBuilder() {
        }

        public ComputerBuilder HDD(String HDD){
            this.HDD = HDD;
            return this;
        }

        public ComputerBuilder RAM(String RAM){
            this.RAM = RAM;
            return this;
        }

        public ComputerBuilder(String hdd, String ram){
            this.HDD=hdd;
            this.RAM=ram;
        }

        public Computer build(){
            return new Computer(HDD, RAM);
        }

    }

}
