// --- Exceptions ---
function InvalidWorkoutException(message) {
  this.name = 'InvalidWorkoutException';
  this.message = message || 'Invalid workout';
}
InvalidWorkoutException.prototype = new Error();

// --- Base Workout ---
class Workout {
  constructor(date = 'N/A', duration = 0.0, notes = '') {
    this.date = date;
    this.duration = duration;
    this.notes = notes;
    this.type = 'Workout';
    this.summary = `${duration} mins`;
  }

  setAll(date, duration, notes) {
    this.date = date; this.duration = duration; this.notes = notes;
  }

  toString() {
    return `Date: ${this.date}, Duration: ${this.duration} mins, Notes: ${this.notes}`;
  }
}

// --- CardioWorkout ---
class CardioWorkout extends Workout {
  constructor(date, duration, notes, distance = 0.0, pace = 0.0) {
    super(date, duration, notes);
    if (duration < 0 || distance < 0 || pace < 0) throw new Error('Workout values cannot be negative.');
    this.distance = distance;
    this.pace = pace;
    this.type = 'CardioWorkout';
    this.summary = `Distance ${distance} mi, Pace ${pace} min/mi`;
  }

  setAll(date, duration, notes, distance, pace) {
    super.setAll(date, duration, notes);
    this.distance = distance;
    this.pace = pace;
    this.summary = `Distance ${distance} mi, Pace ${pace} min/mi`;
  }

  toString() {
    return super.toString() + `, Distance: ${this.distance} miles, Pace: ${this.pace} min/mile`;
  }
}

// --- StrengthWorkout ---
class StrengthWorkout extends Workout {
  constructor(date, duration, notes, sets = 0, reps = 0, weight = 0.0) {
    super(date, duration, notes);
    if (duration < 0 || sets < 0 || reps < 0 || weight < 0) {
      throw new InvalidWorkoutException('Workout values cannot be negative.');
    }
    this.sets = sets;
    this.reps = reps;
    this.weight = weight;
    this.type = 'StrengthWorkout';
    this.summary = `Sets ${sets}, Reps ${reps}, Weight ${weight} lbs`;
  }

  setAll(date, duration, notes, sets, reps, weight) {
    super.setAll(date, duration, notes);
    this.sets = sets; this.reps = reps; this.weight = weight;
    this.summary = `Sets ${sets}, Reps ${reps}, Weight ${weight} lbs`;
  }

  toString() {
    return super.toString() + `, Sets: ${this.sets}, Reps: ${this.reps}, Weight: ${this.weight} lbs`;
  }
}

// --- User ---
class User {
  constructor(name = 'N/A', age = 0, workouts = []) {
    this.name = name;
    this.age = age;
    this.workouts = workouts || [];
  }

  setAll(name, age) { this.name = name; this.age = age; }

  addWorkout(w) { this.workouts.push(w); }

  toString() {
    let result = `User: ${this.name}, Age: ${this.age}\nWorkouts:\n`;
    this.workouts.forEach(w => result += ` - ${w.type}: ${w.summary}\n`);
    return result;
  }
}

// --- Persistence helpers ---
const STORAGE_KEY = 'workoutUser';

function saveUser(user) {
  // convert workouts to plain objects so classes don't get lost; keep "type" field
  const plain = {
    name: user.name,
    age: user.age,
    workouts: user.workouts.map(w => ({ ...w })) // shallow copy
  };
  localStorage.setItem(STORAGE_KEY, JSON.stringify(plain));
}

function loadUser() {
  const raw = localStorage.getItem(STORAGE_KEY);
  if (!raw) return null;
  try {
    const parsed = JSON.parse(raw);
    const user = new User(parsed.name, parsed.age, []);
    parsed.workouts.forEach(w => {
      // reconstruct class instances by type
      if (w.type === 'CardioWorkout') {
        const cw = new CardioWorkout(w.date, w.duration, w.notes, w.distance, w.pace);
        user.addWorkout(cw);
      } else if (w.type === 'StrengthWorkout') {
        try {
          const sw = new StrengthWorkout(w.date, w.duration, w.notes, w.sets, w.reps, w.weight);
          user.addWorkout(sw);
        } catch (err) {
          // if reconstructing fails, convert to plain Workout
          const basic = new Workout(w.date, w.duration, w.notes);
          user.addWorkout(basic);
        }
      } else {
        user.addWorkout(new Workout(w.date, w.duration, w.notes));
      }
    });
    return user;
  } catch (err) {
    console.error('Error loading user:', err);
    return null;
  }
}

function loadUserOrThrow() {
  const user = loadUser();
  if (!user) throw new Error('No user found. Please create a user first.');
  return user;
}