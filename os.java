import java.util.*;

public class Main {
    static class Task implements Comparable<Task> {
        String name;
        int priority;
        double executionTime;
        double energyConsumption;

        public Task(String name, int priority, double executionTime) {
            this.name = name;
            this.priority = priority;
            this.executionTime = executionTime;
            this.energyConsumption = 0.0;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority); // Min-heap
        }
    }

    static class TaskScheduler {
        private double basePower;
        private double cpuFrequency;
        private double voltage;
        private PriorityQueue<Task> taskQueue;
        private List<Task> taskLog;

        public TaskScheduler(double basePower, double cpuFrequency, double voltage) {
            this.basePower = basePower;
            this.cpuFrequency = cpuFrequency;
            this.voltage = voltage;
            this.taskQueue = new PriorityQueue<>();
            this.taskLog = new ArrayList<>();
        }

        public void addTask(Task task) {
            taskQueue.add(task);
        }

        public double calculateEnergy(Task task) {
            if (task.executionTime <= 0) {
                throw new IllegalArgumentException("Execution time must be greater than zero.");
            }
            return basePower * (task.executionTime / cpuFrequency) * Math.pow(voltage, 2);
        }

        public void adjustCpuFrequencyAndVoltage(Task task) {
            if (task.executionTime > 100) {
                this.cpuFrequency = 2.0;
                this.voltage = 1.0;
            } else {
                this.cpuFrequency = 1.0;
                this.voltage = 0.9;
            }
        }

        public void executeTasks() {
            System.out.println("Executing Tasks with Energy-Efficient Scheduling using DVFS...\n");

            while (!taskQueue.isEmpty()) {
                Task task = taskQueue.poll();
                adjustCpuFrequencyAndVoltage(task);
                task.energyConsumption = calculateEnergy(task);
                taskLog.add(task);
                System.out.printf("Executing %s | Priority: %d | Execution Time: %.0f ms | Energy Used: %.2f J\n",
                        task.name, task.priority, task.executionTime, task.energyConsumption);
            }
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler(0.5, 2.5, 1.2);

        scheduler.addTask(new Task("Task A", 2, 120));
        scheduler.addTask(new Task("Task B", 1, 80));
        scheduler.addTask(new Task("Task C", 3, 200));

        scheduler.executeTasks();
    }
}