Open Field Flow
===============

Open Field Flow is a work of abstract art. More precisely, it is a particle system simulator in 
which particles flow through and interact with a vector field. This results in visually interesting patterns.
It is also possible to interact with the vector field, thereby altering the observed flow and engaging the user.

Both the source files and the binaries are provided. If all you want is to run the software, download the
`open_field_flow_v1.0.0.zip` distribution from the list above and follow the instructions below (Usage section).
The other files provide the source program and supporting files. For convenience, a whole NetBeans project
was pushed to GitHub, not only the program (more about this below).

Although this implementation is completely  original, the artistic concept behind it is not. In fact, 
this software was built (from scratch) with the purpose of reproducing the main conceptual aspects of the work of art 
named "pixflow #2", whose author is [LAb\[au\]] (http://lab-au.com/). However, this is done independently and has 
no relationship whatsoever with pixflow #2 other than being inspired by it (more about this below).


Usage
-----

Open Field Flow is implemented in Java. Thus, you need the Java Runtime Environment to run it, which can be 
obtained here: 

   http://www.java.com/

Once Java is installed, there are two methods to run the software: (i) directly from your browser; or (ii) downloading to your 
computer first.


### Method 1: from the browser

Open Field Flow is packaged to support the Java Web Start technology, which allows one to run full Java programs
(not just applets) right from the browser. You can find a demonstration installation at the following URL: 

[http://www.paulosalem.com/openfieldflow/launch.html]

Just go there and click on launch.

### Method 2: download first



Download the file `open_field_flow_v1.0.0.zip` found in the list above. It contains the binary 
distribution for executing the program. Unzip it somewhere and open a command-line in that folder.
You may then run the program by issuing the following command:

  ```shell
    java -jar Open_Field_Flow.jar  -Dsun.java2d.opengl=True
  ```
  
The last option activates graphics hardware acceleration, which improves performance.

Once the program is started, a window will offer a number of options. You may or may not change the default values. 
After that, just press "run simulation" and you should get a fullscreen simulation. To exit the simulation and go 
back to the initial window, just press the Esc key.

Note: On Ubuntu (and possibly other Gnome-based interfaces) some Gnome elements remain visible even on fullscreen.
This is an unsolved bug (on Open Field Flow, Java or Gnome). On Windows 7 (and possibly other versions) 
there is no equivalent problem.


Rationale
----------

I (Paulo Salem) saw pixflow #2 at an art exhibition in São Paulo (Brazil) some years ago and liked the idea so much that I 
soon decided to re-implement at least part of it, for my own personal pleasure. This software is therefore 
primarily intended as an independent, open-source and customized re-implementation of (as well as a tribute to) 
pixflow #2.

Initially, my objective was to create a particle systems engine on which to build different kinds 
of simulations, thus making my version of pixflow merely a special case of a more general tool. However, it is not easy
to come up with interesting artistic concepts. Since I have had very little time to work on this, a condition likely
to remain true for the foreseeable future, I recently decided to release the software as it currently is -- which is, essentially,
my version of pixflow. In this manner, some good is done to the world, though not to my artistic career. 
Nevertheless, it is worth mentioning that:

  * Open Field Flow contains features which I did not see in pixflow, such as user interaction and customization of vector field 
    parameters. These might be original contributions to the underlying concept, but I don't know, since I have no access
    to pixflow's implementation (I just saw the resulting animation at the exhibition).
  * Owing to my original objective, the implementation is quite modular and easily extensible. Particles and vectors,
    for example, are just sub-classes of a more general agent. In fact, I did a number of experiments in this manner,
    but since they were all conceptually half-baked, I did not include them in this release. The hard part
    isn't _how_ to implement, but _what_ to implement.

In order to promote its dissemination and growth, the software is released as open-source, under the GPL 3 license 
(see below). I have opted for the GPL because I feel this particular project ought to remain free. It would be a shame 
to allow it to be closed, since I have created it specifically to make a fine idea (but with an unknown implementation) 
available to me and others.


Contributing
------------
  
As mentioned in the above section, the only reason this software is limited to one kind of simulation is
that the author has no time to build more. In particular, a decent technical foundation is in place
to be used. If you feel like contributing with new kinds of simulations, please feel encouraged to do
so! Other contributions are of course welcomed as well. 

Open Field Flow is implemented in Java 6. Moreover, the NetBeans 7.2 IDE was used to create the GUI, 
so you may consider using it to make your life easier. For further conveience (and perhaps to the detriment of elegance), 
I have pushed the whole NetBeans project to GitHub, not only the source. So all you have to do is to 
download everything and then import the project in NetBeans.


Disclaimer
---------

Neither this software nor its author has any relationship with [LAb\[au\]] (http://lab-au.com/)
or pixflow #2.


License
-------

Open Field Flow - A particle system simulator in which particles 
flow through  and interact with a vector field.
 
Copyright (C) 2012  Paulo Salem (paulosalem@paulosalem.com)

 
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License version 3 
as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.


