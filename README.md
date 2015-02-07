Purposes of this experiment
===

Create a dummy pipeline-like layout using javafx and test it for cross-platform performance and accessibility. 

Accessibility tests
===
There are four main components in the application: the menus, the sidebar, the details pane (also used as the new job pane), and the messages pane.

Launch the GUI by saving the jar file and running it with this command:
java -jar javafx-pipeline-gui-experiment.jar

1. Menus
File: New job, Delete job, Exit (Windows only)
Tests: Trigger GUI change #1, Trigger GUI change #2
On Mac, Quit is used instead of exit and is found under the application menu. It can be accessed with Ctrl + Q.
Delete job is an example of a grayed-out menu item.

The keyboard shortcuts are:
New job: Ctrl + N
Exit: Ctrl + X


2. Sidebar
The sidebar text should be as follows. There are four items in a list view, and each item contains the a visual indicator of the status (colored circle), the script name, and the status text.
Blue circle, DTBook to ZedAI, DONE
Red circle, DAISY 2.02 to EPUB 3, ERROR
Green cirlce, DTBook Validator, RUNNING
Yellow circle, DAISY 3 to EPUB 3, IDLE

3. Details pane
As you select items from the sidebar, are you aware of the details pane contents changing?

Are all the labels accessible on the details pane? Here is an example of what it should say for "DTBook to ZedAI". The control type is given first, followed by the text contents.
Text: Job details
Text: DTBook to ZedAI
Text: Convert DTBook XML to ZedAI XML
List box (3 items):
Input file: source.xml
Assert valid: true
Language: en
Hyperlink: Log file
Hyperlink: Results

Note that the hyperlinks aren't hooked up; i.e. they don't do anything.


4. New job pane
Select "New job" from the file menu

Is the new job form accessible? The controls are as follows:

Text: Choose a script
Combo box with 4 text items:
DTBook to ZedAI
DAISY 2.02 to EPUB 3
DAISY 3 to EPUB 3
DTBook Validator
 
Select a script, and more controls will appear on the form:

Text: Input file
Blank text field
Button: ... (file chooser)
Text: Language code
Blank text field
Checkbox with label "assert valid"
Button: Cancel
Button: Run

Try the file chooser. Activate it using the button with three dots on it and select a file. The path to the file should appear in the blank text field next to the text "input file" after you close the dialog.

The Cancel and Run buttons don't do anything. To navigate away from this form, just choose any job from the sidebar.

5. Messages pane
 Select DTBook to ZedAI from the sidebar and see that the message list pane is accessible, with the following contents:
 "This is a message about DTBook to ZedAI"
 "The job is done"
 "End of messages"

6. GUI Changes
Select "DTBook Validator" from the sidebar

Select "Trigger GUI change #1" from the file menu

Were you aware that the status of the job you are currently viewing has changed from RUNNING to DONE? 

Select "Trigger GUI change #2" from the file  menu

Were you aware that the status of DAISY 3 to EPUB 3 (a job you are not currently viewing in the details window) has changed from IDLE to RUNNING? It is reflected in the sidebar.


