

public class ExerciseRunner {
    private final ExceptionExercises exercises;

    public ExerciseRunner() {
        this.exercises = new ExceptionExercises(new UserService(), new FileService());
    }

    public void runAll() {
        exercises.runExercise11();
        exercises.runExercise12();
        exercises.runExercise13("0");
        exercises.runExercise13("abc");
        exercises.runExercise13("5");
        exercises.runExercise21();
        exercises.runExercise22();
        exercises.runExercise31();
        exercises.runExercise32();
        exercises.runExercise41("personas.txt");
    }
}
