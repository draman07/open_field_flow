Open Field Flow
===============

Open Field Flow is a work of abstract art. More precisely, it is an particle system simulator in 
which particles flow through and interact with a vector field. This results in visually interesting patterns.
It is also possible to interact with the vector field, thereby altering the observed flow and engaging the user.

Although this implementation is completely  original, the artistic concept behind it is not. In fact, 
this software was built with the purpose of reproducing the main aspects of the work of art named "pixflow #2", 
whose author is [LAb\[au\]] (http://lab-au.com/). 


Rationale
----------

I (Paulo Salem) saw pixflow #2 in an exposition in São Paulo (Brazil) some years ago and liked the idea so much that I 
soon decided to re-implement at least part of it, for my own personal pleasure. This software is therefore 
primarily intended as an idependent, open-source and customized re-implementation of (as well as a tribute to) 
pixflow #2.

Initially, my objective was to create a particle systems engine on which to build different kinds 
of simulations, thus making my version of pixflow merely a special case of a more general tool. However, it is not easy
to come up with interesting artistic concepts. Since I have had very little time to work on this, a condition likely
to remain true for the forseable future, I recently decided to release the software as it currently is -- which is, essentially,
my version of pixflow. In this manner, some good is done to the world, though not to my artistic career. 
Nevertheless, it is worth mentioning that:

  * Open Field Flow contains features which I did not see in pixflow, such as user interaction and customization of vector field 
    parameters. These might be original contributions to the underlying concept, but I don't know, since I have no access
    to pixflow's implementation (I just saw the resulting animation in the exposition).
  * Owing to my original objective, the implementation is quite modular and easily extensible. Particles and vectors,
    for example, are just sub-classes of a more general agent. In fact, I did a number of experiments in this manner,
    but since they were all conceptually half-baked, I did not include them in this release. The hard part
    isn't _how_ to implement, but _what_ to implement.


Usage
-----

Open Field Flow is implemented in Java. Thus, you need the Java Runtime Environment to run it, which can be 
obtained here: 

   http://www.java.com/

Once Java is installed, you may run the program by issuing the following command:

  ```shell
    java -jar Open_Field_Flow.jar  -Dsun.java2d.opengl=True
  ```
  
The last option activates graphics hardware acceleration, which improves performance.

Once the program is started, a window will ofter a number of options. You may or may not change the default values. 
After that, just press "run simulation" and you should get a fullscreen simulation. To exit the simulation and go 
back to the initial window, just press the Esc key.

Note: On Ubuntu (and possibly other Gnome-based interfaces) some Gnome elements remain visible even on fullscreen.
This is an unsolved bug (on Open Field Flow, Java or Gnome). On Windows 7 (and possibly other versions) 
there is no equivalent problem.


Contributing
------------
  
As mentioned in the above section, the only reason this software is limited to one kind of simulation is
that the author has no time to build more. In particular, a decent technical foundation is in place
to be used. If you feel like contributing with new kinds of simulations, please feel encouraged to do
so! Other contributions are of course welcomed as well. 

Open Field Flow is implemented in Java 6. The NetBeans IDE was used to create the GUI, so you may consider
using it to make your life easier.


Disclaimer
---------

Neither this software nor its author has any relationship with [LAb\[au\]] (http://lab-au.com/)
or pixflow #2.


License
-------
