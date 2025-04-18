def adjust_cpu_frequency_and_voltage(self, task):
    """Adjusts CPU frequency and voltage based on task priority using DVFS principles."""
    
    if task.priority > 7:
        self.cpu_frequency = 3.5  # High priority task
        self.voltage = 1.3
        level = "High"
    elif task.priority > 4:
        self.cpu_frequency = 2.5  # Medium priority task
        self.voltage = 1.2
        level = "Medium"
    else:
        self.cpu_frequency = 1.5  # Low priority task
        self.voltage = 1.1
        level = "Low"
    
    print(f"[DVFS] Priority Level: {level} | CPU Frequency: {self.cpu_frequency} GHz | Voltage: {self.voltage} V")