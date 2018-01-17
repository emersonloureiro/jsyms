# jsyms

A Java-based systems simulation SDK. What does it mean? It means it provides the basic framework to simulate computer systems - concurrent, distributed, etc - executing over time. It's a slimmed down version of the same framework I've built and used as part of my PhD work - where I used it to simulate the convergence properties of a class of decentralized systems.

To get started, add the following dependency to your project, if you're using Maven.

```xml
<dependency>
	<groupId>cf.janga</groupId>
	<artifactId>jsyms</artifactId>
	<version>0.0.3</version>
</dependency>
```

Alternatively, download the latest release directly from https://github.com/emersonloureiro/jsyms/releases.

# Basics

Simulations in jsyms are based around the idea of _steps_. A step is the time each entity - e.g., a server, a database, a network node - in the system being simulated gets to do something. It's "their turn" to take an _action_. Action here is a lose term, as the semantic and granularity of the action will vary depending on the system being simulated. The imporant thing is that each entity gets a turn over an _iteration_ of the simulation, and by iterating over multiple times, we can simulate a computer system being executed, and in fact any behaviour of such systems, like race conditions in concurrent systems.

# Using jsyms

To illustrate the process of defining and running a simulation from scratch, a well-known problem in Computer Science, the Dining Philosophers problem, will be used. This example, and others, is fully available in the `src/examples` directory.

First you need to think about the entities you'd want to have in the system to be simulated. At the very least, you'll implement those entities by implementing the `Steppable` interface. For the Dining Philosophers problem, the only entity is the `Philosopher`, so its code would look something like this:

```java
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
    public void start() {
    	// Philosopher-specific simulation initialization logic
    }

    @Override
    public void stop() {
    }
}
```

The `step` method is where the Philosopher has a turn to do something, and it's called by the simulation engine on each iteration. In this case the action of each Philosopher during its step is fairly simple; there's a `50/50` chance that the Philosopher will either think or try to hold both forks so that it can eat. When thinking, the Philosopher does nothing but to think. When trying to hold the forks, he will, on each step, do one of:

1. Try to hold the right fork if he doesn't hold it yet
2. Once he holds the right fork, try to hold the left fork if he doesn't hold it yet
3. Eat once he holds both forks
4. Put down the left fork once he has eaten
5. Put down the right fork once he has eaten

Clearly, this is not a deadlock-free solution, but the point here is merely illustrate how the entities tie in with the rest of the simulation.

Next comes setting up and running the simulation - see the code snippet below. The philosophers and fork objects are created, and in particular the philosophers are added to the list of all steppables in the simulation. This list of steppables is given to a "main" steppable - the one we pass on to the simulation - which is the one that steps all of the philosophers. Design-wise, that is merely just a `Steppable` since all the simulation does is step it on each iteration, and in turn it steps each of its own steppables. In this case, this main steppable uses a shuffling strategy to do so, where it shuffles all of the steppables on each iteration to randomize the order on which each philosopher is stepped. Finally the simulation itself is created and ran.

The simulation will run for as long as determined by the conditions object provided. In this example, a `MaxIterationsCondition` is being used, which will run the simulation for a maximum number of iterations. `FinishingCondition` is the actual type that defines the condition, but only the `MaxIterationsCondition` is provided so far. You can implement different conditions to suit your needs though.

```java
public class DiningPhilosophersExample {

    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();
        Philosopher philosopher1 = new Philosopher(1, fork1, fork2);
        Philosopher philosopher2 = new Philosopher(2, fork2, fork3);
        Philosopher philosopher3 = new Philosopher(3, fork3, fork4);
        Philosopher philosopher4 = new Philosopher(4, fork4, fork5);
        Philosopher philosopher5 = new Philosopher(5, fork5, fork1);

        List<Steppable> steppables = new LinkedList<>();
        steppables.add(philosopher1);
        steppables.add(philosopher2);
        steppables.add(philosopher3);
        steppables.add(philosopher4);
        steppables.add(philosopher5);
        CompositeSteppable steppable = new ShufflingCompositeSteppable(steppables);

        Simulation simulation = new Simulation("Dining philosophers", "", steppable, new MaxIterationsCondition(100));
        simulation.run();
    }
}
```

For completeness, the code for the `Fork` class is also included below.

```java
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
```

Below is the last few lines of running the simulation for this example. You can see that all of the philosophers are being stepped over time, but eventually none of them make any progress since each only holds a fork but never lets go of it until it holds another.

```
...
Philosopher 4 failed to take left fork...
Philosopher 1 failed to take left fork...
Philosopher 5 failed to take left fork...
Philosopher 5 thinking...
Philosopher 3 thinking...
Philosopher 2 failed to take left fork...
Philosopher 4 failed to take left fork...
Philosopher 1 failed to take left fork...
Philosopher 3 thinking...
Philosopher 2 thinking...
Philosopher 1 failed to take left fork...
Philosopher 5 thinking...
Philosopher 4 failed to take left fork...
```

# Messaging

I a lot - if not most - systems you'll want to simulate, you'll need to exchange messages between the different entities in your system, so the following example should illustrate how to do that in jsyms. This example will be a simple client-server simulation, where a client sends requests to a load balancer which in turns sends to one or more service instances. The full example is available on the `src/examples` folder.

The communication client-load balancer and load balancer-service instance is all done via message exchange. In this case, the client will send a message to the load balancer - representing a request - which will in turn forward that message/request to one of its service instances.

Let's start by showing how the service instance is implemented - code snippet below. First, our service instance class now inherits from `FifoQueueMessageable` instead of the simple `Steppable`. `FifoQueueMessageable` is in fact an implementation of `Messageable` - the basic interface for entities which can receive messages - where messages are queued in a Fifo queue for processing. The `FifoQueueMessageable` already takes care of what happens during most of the `step` phase, in this case it'll pop a message from the queue and pass it to the `processMessage` for processing, implemented by subclasses. In our case, the service is super simple and simply prints out a message to represent the request being processed and sends a response to the sender - which is included as part of the message. The response contains the source - the service instance - and the originating client.

```java
public class ServiceInstance extends FifoQueueMessageable {

    @Override
    protected void processMessage(Message message) {
        System.out.println("Processed request " + message.getId());
        message.getSource().doMessage(new Response(this, message.getSource()));
    }
}
```

The load balancer - code snippet below - just like the service instance, also extends from `FifoQueueMessageable`. The logic is a little more complicated for each message, where it redirects the message to one of the service instances when the message is a `Request`, and sends a `Response` to the originating client when it's a `Response` - in this case coming from the service instance.

```java
public class LoadBalancer extends FifoQueueMessageable {

    private final Messageable instances_[];

    private int currentInstance_;

    public LoadBalancer(Messageable instances[]) {
        instances_ = instances;
    }

    @Override
    public void start() {
        currentInstance_ = 0;
    }

    @Override
    public void stop() {
    }

    @Override
    protected void processMessage(Message message) {
        if (currentInstance_ == instances_.length) {
            currentInstance_ = 0;
        }
        if (message instanceof Request) {
            instances_[currentInstance_].doMessage(message);
            currentInstance_++;
        } else if (message instanceof Response) {
            Response response = (Response) message;
            response.getClient().doMessage(new Response(this, response.getClient()));
        }
    }
}
```

In terms of messaging, the client is similar, in which it has a reference to the load balancer - which is a `Messageable` - and with a given chance it will send a request message to the load balancer for processing. The main difference is that the `Client` implements the `step` phase so it can determine the logic for when to send a request. The code for setting up is in the same format as the previous example, and you can fully check it out under `src/examples/clientserver`.

```java
public class Client implements Steppable, Messageable {

    private final float requestChance_;
    private final LoadBalancer loadBalancer_;

    public Client(float requestChance, LoadBalancer loadBalancer) {
        if (requestChance > 1) {
            throw new RuntimeException("Request chance needs to be between 0 and 1");
        }
        requestChance_ = requestChance;
        loadBalancer_ = loadBalancer;
    }

    @Override
    public void start() {
    }

    @Override
    public void step() {
        if (Math.random() < requestChance_) {
            System.out.println("Client issued request...");
            loadBalancer_.doMessage(new Request(this));
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void doMessage(Message message) {
        if (message instanceof Response) {
            System.out.println("Received response...");
        }
    }
}
```