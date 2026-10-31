-- ===========================
-- USERS
-- ===========================
insert into users (user_id, name, tel, type, skill, age, weight, allergies)
values
-- Doctors
(1, 'Dr. Alice Martin', '514-111-1111', 'doctor', 'Cardiology', null, null, null),
(2, 'Dr. Jean Tremblay', '514-222-2222', 'doctor', 'Family Medicine', null, null, null),

-- Patients
(3, 'Marie Dubois', '438-333-3333', 'patient', null, 34, 65, 'penicillin'),
(4, 'Luc Bouchard', '438-444-4444', 'patient', null, 58, 82, 'none');

-- ===========================
-- DRUGS
-- ===========================
insert into drugs (drug_id, name, standard_dose_per_kg)
values
(1, 'Amoxicillin', 10.00),   -- mg/kg
(2, 'Ibuprofen', 5.00),      -- mg/kg
(3, 'Metformin', 15.00),     -- mg/kg
(4, 'Atorvastatin', 1.00),   -- mg/kg
(5, 'Warfarin', 0.20);       -- mg/kg

-- ===========================
-- PRESCRIPTIONS
-- ===========================
-- pdd = prescribed daily dose (absolute, not per kg)
insert into prescriptions (prescription_id, pdd, doctor_id, patient_id)
values
(1, 650.00, 1, 3),  -- Dr Alice -> Marie
(2, 1500.00, 2, 4); -- Dr Jean -> Luc

-- ===========================
-- ADVERSE DRUG REACTIONS
-- ===========================
insert into adverse_drug_reactions (adr_id, category, description)
values
(1, 'Gastrointestinal', 'Nausea and diarrhea'),
(2, 'Gastrointestinal', 'Stomach irritation'),
(3, 'Metabolic', 'Hypoglycemia (low blood sugar)'),
(4, 'Musculoskeletal', 'Muscle pain'),
(5, 'Hematologic', 'Increased bleeding risk');

-- ===========================
-- PRESCRIPTIONS_DRUGS (join)
-- ===========================
insert into prescriptions_drugs (prescription_id, drug_id)
values
-- Prescription 1 (Marie)
(1, 1),  -- Amoxicillin
(1, 2),  -- Ibuprofen

-- Prescription 2 (Luc)
(2, 3),  -- Metformin
(2, 4);  -- Atorvastatin

-- ===========================
-- ADRS_PRESCRIPTIONS (join)
-- ===========================
insert into adrs_prescriptions (prescription_id, adr_id)
values
-- Marie: nausea from Amoxicillin
(1, 1),
-- Luc: muscle pain from Atorvastatin
(2, 4);

-- ===========================
-- DRUG_INTERACTIONS
-- ===========================
-- factor > 1 = increases effect/toxicity, < 1 = decreases
insert into drug_interactions (drug_id, interacting_drug, factor)
values
-- Warfarin interactions
(5, 'Ibuprofen', 1.5),   -- ↑ bleeding risk
(5, 'Amoxicillin', 1.2), -- mild ↑ effect

-- Atorvastatin interactions
(4, 'Metformin', 1.1);