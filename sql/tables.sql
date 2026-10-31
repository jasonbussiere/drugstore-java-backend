drop table if exists drug_interactions;
drop table if exists adrs_prescriptions;
drop table if exists prescriptions_drugs;
drop table if exists prescriptions;
drop table if exists users;
drop table if exists drugs;
drop table if exists adverse_drug_reactions;

create table users
(
    user_id int primary key auto_increment,
    name varchar(255) not null ,
    tel varchar(255),

    /*inheritance discriminator*/
    type enum('patient', 'doctor') not null,

    /* doctor fields (nullable) */
    skill varchar(255),

    /* patient field (nullable) */
    age int,
    weight int,
    allergies varchar(255)
);

create table drugs
(
    drug_id int primary key auto_increment,
    name varchar(255) not null,
    standard_dose_per_kg decimal(8,2) not null
);

create table prescriptions
(
    prescription_id int primary key auto_increment,
    pdd decimal(8,2),

    doctor_id int,
    patient_id int,

    foreign key (patient_id) references users(user_id),
    foreign key (doctor_id) references users(user_id)

);

create table adverse_drug_reactions
(
    adr_id int primary key auto_increment,
    category varchar(255),
    description varchar(255)
);

create table prescriptions_drugs
(
    prescription_id int,
    drug_id int,

    primary key (prescription_id, drug_id),

    foreign key (prescription_id) references prescriptions(prescription_id),
    foreign key (drug_id) references drugs(drug_id)
);

create table adrs_prescriptions
(
    prescription_id int,
    adr_id int,

    primary key (prescription_id, adr_id),

    foreign key (prescription_id) references prescriptions(prescription_id),
    foreign key (adr_id) references adverse_drug_reactions(adr_id)
);

Create table drug_interactions(
    drug_id int not null,
    interacting_drug varchar(255),
    factor double,

    foreign key (drug_id) references drugs(drug_id)
);
