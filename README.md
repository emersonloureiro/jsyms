# jsyms

A Java-based systems simulation SDK. What does it mean? It means it provides the basic framework to simulate computer systems - concurrent, distributed, etc - executing over time. It's a slimmed down version of the same framework I've built and used as part of my PhD work - where I used it to simulate the convergence properties of a class of decentralized systems.

# Core Concepts

Simulations in jsyms are based around the idea of _steps_. A step is the time each entity - e.g., a server, a database, a network node - in the system being simulated gets to do something. It's "their turn" to take an _action_. Action here is a lose term, as the semantic and granularity of the action will vary depending on the system being simulated. The imporant thing is that each entity gets a turn over an _iteration_ of the simulation, and by iterating over multiple times, we can simulate a computer system being executed, and in fact any behaviour of such systems, like race conditions in concurrent systems.

This documentation is a work in progress, so I'd invite anyone interested in learning how to use the SDK to check out the `src/examples` directory for complete working examples of how to use the framework.