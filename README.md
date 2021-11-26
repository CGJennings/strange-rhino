# Strange Rhino

![](readme/images/rhino.png)

Strange Rhino is a fork of [Mozilla Rhino](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino) that has been customized for its role as the script engine for [Strange Eons](https://cgjennings.github.io/se3docs/index.html).

## What's different?

### Language

On the language side, the main difference between the two is that Strange Rhino accepts some non-standard syntax:

- Identifiers may start with `@` or `#` in addition to the usual characters.
- Identifiers that start with `$`, `@`, or `#` can include hyphens `-`.

In Strange Eons, these identifiers have special meaning:

`$`  
Identifiers starting in `$` are used to look up *settings*, string-based properties that are automatically saved with components, as well as being used to store user preferences and the configuration of project and task folders.

`@`  
Identifiers starting with `@` are used to look up strings in the database of user interface strings. For example, a dialog box button might be given a label `button.text = @accept-changes`.

`#`  
Similarly, identifiers starting with `#` are used to look up strings in the database of game language strings.

> This means that E4X support support is not possible in Strange Rhino. If private properties are ever added to Rhino using the current proposed syntax, it is likely that this will also cause compatibility issues.

### Implementation notes

#### Debugger changes

In order to interoperate with the Strange Eons [script debugger](https://cgjennings.github.io/se3docs/dm-debugger.html), the adapter classes in `javascript/tools/debugger` have been modified and/or replaced. The following classes are kept (with modifications): `Dim`, `GuiCallback`, `ScopeProvider`, `SourceProvider`. The Rhino debugger is not used, and its sources and dependencies have been removed: `Main`, `SwingGui`, `test.js` and the entire contents of the `treetable` package.

#### JSR 223

The contents of the `ca.cgjennings.script.util` package bridge between Strange Rhino and Strange Eons via the JSR 223 scripting interface. These classes were adapted from the reference implementation that was used to add a variant of Rhino to Java 6 for JSR 223.

#### Package names

Up to the commit tagged `v1.7.9r`, Strange Rhino used a different package hierarchy to prevent confusion with other variants of Rhino. The package root was `ca.cgjennings.script.mozilla` instead of `org.mozilla`.

This practice stopped with the commit tagged `v1.7.9u` to make it easier to merge in upstream changes. Updating existing code should require little more than replacing the string `ca.cgjennings.script.mozilla` with `org.mozilla`, and likewise `ca/cgjennings/script/mozilla` with `org/mozilla`.

## License information

Strange Rhino and Mozilla Rhino are licensed under MPL 2.0. The original Rhino license and notices can be found under `src/license-metadata`. Rhino image adapted from an icon by [Freepik](https://www.flaticon.com/) under Creative Commons BY 3.0.