# Project notes

## Color Theming
* Color should be defined at the model level because this is the object accessible to the NodeContainer at render time. Then the NodeContainer creates the panel and inside this the render to the node type. Both know the node element so if the color is there then the NodeContainer can render the right color.
