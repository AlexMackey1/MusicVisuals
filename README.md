# Music Visualiser Project

| Name | Student Number |
|-----------|-----------|
| Alex Mackey | C22739165 |
| Sergei Larionov | C22483302 |
| Aaron Connolly | C22410766 |



# Description of the assignment
This is our visualization of Bohemian Rhapsody by Queen. We felt that this song chnages drastcially minute to minute so
therefore it would be the best choice to do a visualization project on. Due to all of the chnanges we would be able to showcase
everything that we learnt as the song transitioned between different beats, moods and speeds etc.

# Instructions
Our song changes at specific intervals that we chose below.

| TimeStamp | Visual | Mode |
|-----------|-----------|-----------|
| 0:00 - 0:16 |Intro | 4 |
| 0:16 - 1:57 | Alex | 0 |
| 1:57 - 3:04 | Sergei | 3 |
| 3:04 - 4:09 | Sergei | 2 |
| 4:09 - 5:59 | Aaron | 1 |

Apart from that you can use "p" to pause the audio and you can use the number keys above to manually change between the visualizations.

### How it works
# Alex
My code used the OOP principles of encapsulation and inheritance. The majority of the code is the "BoxClass" and "RotatingCircles" classes. These are then called on in "Alex" class which is my visulation. It comprises of 4 boxsets from the Boxclass class, that rotate at different speeds and change colours based on amplitude and frquency bands. These box sets have a set position within the visual. The RotatingCircles class draws several circles that each correspond to a different frequency band. The size of the circles and the colours are mapped based on the frquency bands. The circles then swirl around somewhat passively but this leads to some very cool effects at time. The "Alex" class calls on the circle and box classes and visualises them on the screen. It also calculated the Fast Fourier Transform which calculates all the different frequency bands.

![An image](java/data/images/AlexImg1.png)

![An image](java/data/images/AlexImg2.png)

![An image](java/data/images/AlexImg3.png)

# Sergei
The first visual consists of multiple shapes, boxes, spheres and cylinders which spin and orbit at differnet speeds based on the amplitude, each shape has their own segment using the frequency bands which allows them to change differently based on the frequency which gives it an orchestra feeling which matches the part of the song it plays during. The colors are also based on the amplitude giving it an rgb effect. The shapes are placed using translate and an increment in the angle to give it a eplise shape.

The second visual consists of a spinning pyramid with a floating eye above and a wavy landscape beneath. The outer eye is created using an elipse and the pupil has a pulsing effect which is from using the map feature and the amplitude. It also follows the mouse cursor by mapping the coordinates of the cursor and adjusting the eye size. The pyramids rotation and visual dynamics are achieved through continuous updates to the vertex positions (angle1, angle2, angle3) based on the amplitude. The wavy landscape is generated using the noise and map feature to create dynamic wave patterns across horizontal sections of the screen. Each wave's height is influenced by the audio amplitude giving it a wavy/hilly look. The color of the landscape is also based on the amplitude leaving it red at its quietist moments and blue during the loudest moments.

![An image](java/data/images/Serg1.png)
![An image](java/data/images/Serg2.png)
![An image](java/data/images/Serg3.png)
![An image](java/data/images/Serg4.png)

# Aaron


# What we are most proud of in the assignment
We are most proud of using everything we learned this year to create a music visualization that uses OOP principles, primarily encapsulation and inheritance. We are proud of how the visual turned out and the way that the visualization came together. We like how the visuals start of quite tame and calm just like the song does. Then when the tempo and beat get more dramatic so do the visuals which is only fitting for a song like "Bohemian Rhapsody". 

# Please see our youtube video below
[![YouTube](java/data/images/Youtube.png)](https://www.youtube.com/watch?v=2o4Mb21h-a0&list=WL&index=77)


# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

