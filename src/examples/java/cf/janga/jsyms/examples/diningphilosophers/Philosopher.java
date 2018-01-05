package cf.janga.jsyms.examples.diningphilosophers;

import cf.janga.jsyms.core.Steppable;

/**
 * Represents a philosopher in the dining philosopher problem.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Philosopher implements Steppable {

    private final Fork leftFork_;
    private final Fork rightFork_;
    private final int number_;
    private boolean eating_;

    public Philosopher(int number, Fork rightFork, Fork leftFork) {
        rightFork_ = rightFork;
        leftFork_ = leftFork;
        number_ = number;
        eating_ = false;
    }

    @Override
    public void start() {
    }

    @Override
    public void step() {
        if (Math.random() < 0.5) {
            System.out.println("Philosopher " + number_ + " thinking...");
        } else {
            if (rightFork_.holds(this)) {
                if (leftFork_.holds(this)) {
                    if (!eating_) {
                        System.out.println("Philosopher " + number_ + " eating...");
                        eating_ = true;
                    } else {
                        leftFork_.putDown(this);
                        System.out.println("Philosopher " + number_ + " put down left fork...");
                    }
                } else {
                    if (!eating_) {
                        if (leftFork_.take(this)) {
                            System.out.println("Philosopher " + number_ + " took left fork...");
                        } else {
                            System.out.println("Philosopher " + number_ + " failed to take left fork...");
                        }
                    } else {
                        rightFork_.putDown(this);
                        eating_ = false;
                        System.out.println("Philosopher " + number_ + " put down right fork...");
                    }
                }
            } else {
                if (rightFork_.take(this)) {
                    System.out.println("Philosopher " + number_ + " took right fork...");
                } else {
                    System.out.println("Philosopher " + number_ + " failed to take right fork...");
                }
            }
        }
    }

    @Override
    public void stop() {
    }
}
