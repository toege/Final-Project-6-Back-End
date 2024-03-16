DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM workouts;
DELETE FROM muscles;


INSERT INTO roles
(name)
VALUES
('ROLE_USER'),
('ROLE_MODERATOR'),
('ROLE_ADMIN');

INSERT INTO muscles (muscle_Name, muscle_Group) VALUES
('Biceps Brachii', 'Upper Arm'),        -- ID 1
('Triceps Brachii', 'Upper Arm'),       -- ID 2
('Deltoid', 'Shoulder'),                -- ID 3
('Pectoralis Major', 'Chest'),          -- ID 4
('Latissimus Dorsi', 'Back'),           -- ID 5
('Quadriceps Femoris', 'Legs - Front'), -- ID 6
('Hamstrings', 'Legs - Back'),          -- ID 7
('Gastrocnemius', 'Legs - Calves'),     -- ID 8
('Rectus Abdominis', 'Abs');            -- ID 9

-- Workouts for Biceps Brachii
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Concentration Curl', 'Isolates the biceps using dumbbells with seated position', (SELECT id FROM muscles WHERE muscle_Name = 'Biceps Brachii')),
('Preacher Curl', 'Curls performed on a preacher bench to target the biceps', (SELECT id FROM muscles WHERE muscle_Name = 'Biceps Brachii')),
('Cable Curl', 'Using a cable machine to perform curls for constant tension', (SELECT id FROM muscles WHERE muscle_Name = 'Biceps Brachii')),
('Zottman Curl', 'Combines a regular curl and reverse curl in one movement', (SELECT id FROM muscles WHERE muscle_Name = 'Biceps Brachii'));

-- Workouts for Triceps Brachii
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Skull Crusher', 'Lying tricep extension with a barbell or dumbbells', (SELECT id FROM muscles WHERE muscle_Name = 'Triceps Brachii')),
('Close-Grip Bench Press', 'Bench press with a closer grip to target triceps', (SELECT id FROM muscles WHERE muscle_Name = 'Triceps Brachii')),
('Tricep Kickback', 'Bending forward and kicking the dumbbell back', (SELECT id FROM muscles WHERE muscle_Name = 'Triceps Brachii')),
('Rope Pushdown', 'Using a cable machine with a rope attachment for triceps', (SELECT id FROM muscles WHERE muscle_Name = 'Triceps Brachii'));

-- Workouts for Deltoid
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Front Raise', 'Lifting weights straight in front to shoulder height', (SELECT id FROM muscles WHERE muscle_Name = 'Deltoid')),
('Reverse Pec Deck Fly', 'Targets the rear deltoids on a pec deck machine', (SELECT id FROM muscles WHERE muscle_Name = 'Deltoid')),
('Arnold Press', 'A rotational shoulder press named after Arnold Schwarzenegger', (SELECT id FROM muscles WHERE muscle_Name = 'Deltoid')),
('Upright Row', 'Lifting weights vertically close to the body to target shoulders', (SELECT id FROM muscles WHERE muscle_Name = 'Deltoid'));

-- Workouts for Pectoralis Major
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Incline Bench Press', 'Bench press on an incline bench to target upper chest', (SELECT id FROM muscles WHERE muscle_Name = 'Pectoralis Major')),
('Decline Push Up', 'Push-ups with feet elevated to target lower chest', (SELECT id FROM muscles WHERE muscle_Name = 'Pectoralis Major')),
('Cable Fly', 'Performing flyes with a cable machine to target the chest', (SELECT id FROM muscles WHERE muscle_Name = 'Pectoralis Major')),
('Chest Dip', 'Dips that focus on the chest by leaning forward', (SELECT id FROM muscles WHERE muscle_Name = 'Pectoralis Major'));

-- Workouts for Latissimus Dorsi
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Lat Pulldown', 'Using a lat pulldown machine to target the back', (SELECT id FROM muscles WHERE muscle_Name = 'Latissimus Dorsi')),
('Single-Arm Dumbbell Row', 'Rowing with a dumbbell, one arm at a time', (SELECT id FROM muscles WHERE muscle_Name = 'Latissimus Dorsi')),
('T-Bar Row', 'Rowing a T-bar machine or barbell for mid-back', (SELECT id FROM muscles WHERE muscle_Name = 'Latissimus Dorsi')),
('Chin-Up', 'Pulling oneself up on a bar with palms facing towards you', (SELECT id FROM muscles WHERE muscle_Name = 'Latissimus Dorsi'));

-- Workouts for Quadriceps Femoris
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Front Squat', 'Squat with the weight held in front of the shoulder', (SELECT id FROM muscles WHERE muscle_Name = 'Quadriceps Femoris')),
('Walking Lunge', 'Performing lunges in a walking motion to target the quads', (SELECT id FROM muscles WHERE muscle_Name = 'Quadriceps Femoris')),
('Bulgarian Split Squat', 'A lunge performed with the rear leg elevated', (SELECT id FROM muscles WHERE muscle_Name = 'Quadriceps Femoris')),
('Leg Extension', 'Using a leg extension machine to isolate the quads', (SELECT id FROM muscles WHERE muscle_Name = 'Quadriceps Femoris'));

-- Workouts for Hamstrings
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Good Morning', 'Bending forward with a barbell on the shoulders, targeting hamstrings', (SELECT id FROM muscles WHERE muscle_Name = 'Hamstrings')),
('Stiff-Leg Deadlift', 'Deadlift variation focusing on hamstring stretch', (SELECT id FROM muscles WHERE muscle_Name = 'Hamstrings')),
('Glute-Ham Raise', 'A bodyweight exercise targeting hamstrings and glutes', (SELECT id FROM muscles WHERE muscle_Name = 'Hamstrings')),
('Nordic Hamstring Curl', 'Partner-assisted hamstring curl performed on the ground', (SELECT id FROM muscles WHERE muscle_Name = 'Hamstrings'));

-- Workouts for Gastrocnemius
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Donkey Calf Raise', 'Calf raise variant where the user leans forward', (SELECT id FROM muscles WHERE muscle_Name = 'Gastrocnemius')),
('Jump Rope', 'Using a jump rope which targets the calves with each jump', (SELECT id FROM muscles WHERE muscle_Name = 'Gastrocnemius')),
('Box Jump', 'Jumping onto and off of a box, targeting the lower leg', (SELECT id FROM muscles WHERE muscle_Name = 'Gastrocnemius')),
('Calf Press On The Leg Press Machine', 'Pressing with the legs on a leg press machine focusing on the calves', (SELECT id FROM muscles WHERE muscle_Name = 'Gastrocnemius'));

-- Workouts for Rectus Abdominis
INSERT INTO workouts (workout_Name, description, muscle_id) VALUES
('Leg Raise', 'Lifting legs to target the lower abdomen', (SELECT id FROM muscles WHERE muscle_Name = 'Rectus Abdominis')),
('Bicycle Crunch', 'A crunch variation mimicking a cycling motion', (SELECT id FROM muscles WHERE muscle_Name = 'Rectus Abdominis')),
('Hanging Leg Raise', 'Raising legs while hanging from a pull-up bar', (SELECT id FROM muscles WHERE muscle_Name = 'Rectus Abdominis')),
('Russian Twist', 'Twisting the torso with weight for oblique and abs', (SELECT id FROM muscles WHERE muscle_Name = 'Rectus Abdominis'));

