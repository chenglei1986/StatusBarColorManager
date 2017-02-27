# StatusBarColorManager

[ ![Download](https://api.bintray.com/packages/darren/maven/StatusBarColorManager/images/download.svg) ](https://bintray.com/darren/maven/StatusBarColorManager/_latestVersion)

## About

A library which help you to change status bar color in a simple way.

- No `style.xml`, just java code.
- The status bar color can be changed more than once in an Activity.
- If you set a light color even WHITE, it will know.
- Support **MIUI** and **Flyme OS**.
- I don't like the `NavigationBar`, so it's better to keep it in black.

## Preview

![gif](screenshots/gif.gif)

### Colored status bar

![KitKat](screenshots/kitkat.png) ![Lollipop](screenshots/lollipop.png) ![Marshmallow](screenshots/marshmallow.png)

### Layout fullscreen

#### Lollipop

![Lollipop dark](screenshots/layout_fullscreen_dark_lollipop.png) ![Lollipop light](screenshots/layout_fullscreen_light_lollipop.png)

#### Marshmallow

![Marshmallow dark](screenshots/layout_fullscreen_dark_marshmallow.png) ![Marshmallow light](screenshots/layout_fullscreen_light_marshmallow.png)

## Download

```gradle
compile 'com.github.chenglei1986.statusbar:library:1.0.2'
```


## Usage

#### In your Activity

```java
StatusBarColorManager statusBarColorManager = new StatusBarColorManager(activity);
boolean layoutFullscreen = true; // or false
boolean withActionBar = false; // or true
statusBarColorManager.setStatusBarColor(color, layoutFullscreen, withActionBar);
```

## License

MIT License

    Copyright (c) 2016 Darren

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
