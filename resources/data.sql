INSERT INTO patient (name, birthdate, email, gender, bloodgroup)
VALUES ('Amit Sharma', '2000-05-15', 'amit.sharma@example.com', 'Male', 'A_POSITIVE')
,('Priya Verma', '1998-11-23', 'priya.verma@example.com', 'Female', 'B_POSITIVE')
,('Rahul Mehta', '2002-03-10', 'rahul.mehta@example.com', 'Male', 'O_POSITIVE')
,('Sneha Gupta', '1999-07-30', 'sneha.gupta@example.com', 'Female', 'AB_POSITIVE')
,('Karan Patel', '2001-01-25', 'karan.patel@example.com', 'Male', 'B_POSITIVE');

INSERT INTO doctor (name, specialization, email) VALUES
('Dr. Rahul Sharma', 'Cardiologist', 'rahul.sharma@example.com'),
('Dr. Ananya Verma', 'Dermatologist', 'ananya.verma@example.com'),
('Dr. Arjun Mehta', 'Neurologist', 'arjun.mehta@example.com');


INSERT INTO appointment (appointment_time, reason, patient_id, doctor_id)
VALUES
('2025-09-08 10:30:00', 'General Checkup', 1, 1),
('2025-09-08 11:00:00', 'Flu Symptoms', 2, 2),
('2025-09-08 12:15:00', 'Follow-up', 3, 3);


