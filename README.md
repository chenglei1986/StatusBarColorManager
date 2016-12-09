# StatusBarColorManager

## About

A library which help you to change status bar in a simple way.

- No `style.xml`, just java code.
- The status bar color can be changed more than once in an Activity.
- Support **MIUI** and **Flyme OS**.
- I don't like the `NavigationBar`, so it's better to keep it in black.

## Preview

![KitKat](screenshots/kitkat.png) ![Lollipop](screenshots/lollipop.png) ![Marshmallow](screenshots/marshmallow.png)

## Usage

#### In your Activity

```java
StatusBarColorManager statusBarColorManager = new StatusBarColorManager(activity);
statusBarColorManager.setStatusBarColor(color, withActionBar);
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