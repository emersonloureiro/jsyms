package cf.janga.jsyms.examples.diningphilosophers;

import java.util.Optional;

/**
 * Represents a fork in the dining philosophers problem.
 *
 * @author Emerson Loureiro (emerson.loureiro@gmail.com).
 */
public class Fork {

    private Optional<Philosopher> heldBy_;

    public Fork() {
        heldBy_ = Optional.empty();
    }

    public boolean take(Philosopher philosopher) {
        if (heldBy_.isPresent()) {
            return false;
        } else {
            heldBy_ = Optional.of(philosopher);
            return true;
        }
    }

    public void putDown(Philosopher philosopher) {
        heldBy_ = Optional.empty();
    }

    public boolean holds(Philosopher philosopher) {
        return heldBy_.isPresent() && heldBy_.get() == philosopher;
    }
}
