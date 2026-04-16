-- Create Database
CREATE DATABASE hospital_management;
USE hospital_management;

-- Patients Table
CREATE TABLE Patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    phone VARCHAR(15),
    city VARCHAR(50)
);

-- Doctors Table
CREATE TABLE Doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_name VARCHAR(100),
    specialization VARCHAR(100),
    experience_years INT
);

-- Appointments Table
CREATE TABLE Appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE,
    status VARCHAR(20),
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id)
);

-- Treatments Table
CREATE TABLE Treatments (
    treatment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_id INT,
    diagnosis VARCHAR(100),
    treatment_date DATE,
    cost DECIMAL(10,2),
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctors(doctor_id)
);

-- Insert Patients
INSERT INTO Patients (patient_name, age, gender, phone, city) VALUES
('Arun Kumar', 30, 'Male', '9876543210', 'Coimbatore'),
('Priya S', 25, 'Female', '9876543211', 'Madurai'),
('Rahul M', 40, 'Male', '9876543212', 'Chennai'),
('Divya R', 35, 'Female', '9876543213', 'Salem'),
('Kavin T', 29, 'Male', '9876543214', 'Erode');

-- Insert Doctors
INSERT INTO Doctors (doctor_name, specialization, experience_years) VALUES
('Dr. Meena', 'Cardiology', 12),
('Dr. Ravi', 'Orthopedics', 10),
('Dr. Anitha', 'Dermatology', 8),
('Dr. Suresh', 'General Medicine', 15);

-- Insert Appointments
INSERT INTO Appointments (patient_id, doctor_id, appointment_date, status) VALUES
(1, 4, '2026-01-10', 'Completed'),
(2, 1, '2026-01-15', 'Completed'),
(3, 2, '2026-02-05', 'Completed'),
(1, 1, '2026-02-18', 'Completed'),
(4, 3, '2026-03-01', 'Completed'),
(5, 4, '2026-03-10', 'Completed'),
(2, 4, '2026-03-12', 'Completed'),
(3, 1, '2026-03-15', 'Completed');

-- Insert Treatments
INSERT INTO Treatments (patient_id, doctor_id, diagnosis, treatment_date, cost) VALUES
(1, 4, 'Fever', '2026-01-10', 500.00),
(2, 1, 'Heart Checkup', '2026-01-15', 2500.00),
(3, 2, 'Fracture', '2026-02-05', 4000.00),
(1, 1, 'BP Issue', '2026-02-18', 1500.00),
(4, 3, 'Skin Allergy', '2026-03-01', 1200.00),
(5, 4, 'Cold', '2026-03-10', 600.00),
(2, 4, 'Fever', '2026-03-12', 700.00),
(3, 1, 'Heart Checkup', '2026-03-15', 2600.00);

-- Most Consulted Doctor
SELECT d.doctor_name, COUNT(a.appointment_id) AS total_consultations
FROM Doctors d
JOIN Appointments a ON d.doctor_id = a.doctor_id
GROUP BY d.doctor_name
ORDER BY total_consultations DESC;

-- Monthly Revenue
SELECT 
    YEAR(treatment_date) AS year,
    MONTH(treatment_date) AS month,
    SUM(cost) AS total_revenue
FROM Treatments
GROUP BY YEAR(treatment_date), MONTH(treatment_date);

-- Most Common Diseases
SELECT diagnosis, COUNT(*) AS total_cases
FROM Treatments
GROUP BY diagnosis
ORDER BY total_cases DESC;

-- Patient Visit Frequency
SELECT p.patient_name, COUNT(a.appointment_id) AS visit_count
FROM Patients p
JOIN Appointments a ON p.patient_id = a.patient_id
GROUP BY p.patient_name
ORDER BY visit_count DESC;

-- Doctor Performance
SELECT 
    d.doctor_name,
    COUNT(DISTINCT a.appointment_id) AS appointments,
    COUNT(DISTINCT t.treatment_id) AS treatments,
    COALESCE(SUM(t.cost),0) AS revenue
FROM Doctors d
LEFT JOIN Appointments a ON d.doctor_id = a.doctor_id
LEFT JOIN Treatments t ON d.doctor_id = t.doctor_id
GROUP BY d.doctor_name;