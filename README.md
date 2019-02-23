# Strange Rhino

![Strange Rhino logo; adapted from an icon by Freepik (https://www.flaticon.com/). Creative Commons BY 3.0](readme/images/rhino.png)

Strange Rhino is the fork of [Mozilla Rhino](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino) that has been customized for its role as the script engine for [Strange Eons](https://cgjennings.github.io/se3docs/index.html).

## Differences

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

To prevent the possibility of confusing Mozilla Rhino and Strange Rhino, Strange Rhino has been moved to the package `ca.cgjennings.script.mozilla` (instead of `org.mozilla`).

> To incorporate updates, you must not only update the folder structure and `org.mozilla` references in the code, but you must also update the many string literal class references used for code generation (`"Lorg/mozilla/..."`).

In order to interoperate with the Strange Eons [script debugger](https://cgjennings.github.io/se3docs/dm-debugger.html), the adapter classes in `javascript/tools/debugger` have been modified and/or replaced. The following classes are kept (with modifications): `Dim`, `GuiCallback`, `ScopeProvider`, `SourceProvider`. The Rhino debugger is not used, and its sources and dependencies have been removed: `Main`, `SwingGui`, `test.j` and the entire contents of the `treetable` package.

The contents of the `mozilla/util` package have been added in order to bridge between (Strange) Rhino and Strange Eons via the JSR 223 scripting interface. These are adapted from the reference implementation for adding Rhino to JSR 223.

## License information

Strange Rhino and Mozilla Rhino are licensed under MPL 2.0. The original Rhino license and notices can be found under `src/license-metadata`.