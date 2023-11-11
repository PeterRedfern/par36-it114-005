<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone 2</td></tr>
<tr><td> <em>Student: </em> Peter Redfern (par36)</td></tr>
<tr><td> <em>Generated: </em> 11/11/2023 1:54:33 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-chatroom-milestone-2/grade/par36" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp; <a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T17.45.11image.png.webp?alt=media&token=94725dd8-5149-4846-8f48-7954bc4f302c"/></td></tr>
<tr><td> <em>Caption:</em> <p>PayloadType and ClientName getters and setters<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T17.45.53image.png.webp?alt=media&token=3fd3e885-0653-4dd2-8020-d741953bccfe"/></td></tr>
<tr><td> <em>Caption:</em> <p>clientID and message getters and setters as well as toString override<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T17.51.35image.png.webp?alt=media&token=22d0dbd4-75e0-4262-9e3d-57f6c9345568"/></td></tr>
<tr><td> <em>Caption:</em> <p>The debug information above each message (in the Type[ ] section) shows the<br>payloads: CONNECT, MESSAGE, CLIENT_ID, RESET_USER_LIST, CREATE_ROOM, JOIN_ROOM<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Server-side commands </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the mentioned commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T17.59.15image.png.webp?alt=media&token=680acf28-de0a-4b3c-a39f-e6f2e601ade7"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the roll code which has an if/else statement to support either one<br>integer for one die or #d# for multiple dice. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T18.00.46image.png.webp?alt=media&token=ef923d00-d9e1-4d46-9400-9a65326468c8"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the flip code which uses an if/else statement and a random variable<br>to determine heads or tails. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Explain the logic/implementation of each commands</td></tr>
<tr><td> <em>Response:</em> <ol><br><li>/roll works by checking what the user enters after roll. If the<br>user does not enter &quot;d&quot;/only one number, the command goes to the else<br>statement which takes in that one number input and uses it to calculate<br>the total which is the input multiplied by Math.random(). From there the total<br>is printed out in a roll message saying what the user rolled. For<br>multiple dice, the if statement to see if the user input a d<br>and two numbers with another nested if statement is used. From there, the<br>numbers around the d are split with regex and put into two variables.<br>The first number is the number of dice and the next number is<br>the number of sides on the dice. From there, the max and min<br>are determined by diceNum * diceSides and diceNum respectively. That max and min<br>are then used in the calculation of the roll by doing Math.random() *<br>(max - min + 1)) + min, which is then put into the<br>total variable. From there the result is printed out in a roll message<br>saying what the user rolled.&nbsp;<div><br></div><div>2. /flip works by creating a new random object<br>which generates a random int between 0 and 1. This is then put<br>into an if/else statement which says if the value is equal to 0<br>print out &quot;heads&quot; but if it isn&#39;t (which means it&#39;s 1) print out<br>tails. From there the result is printed out to the user which tells<br>the user what the coin landed on.&nbsp;</div><br></li><br></ol><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Text Display </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the various style handling via markdown or special characters</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T18.22.46image.png.webp?alt=media&token=6d01c3f1-b626-4635-9167-cd017db3cc01"/></td></tr>
<tr><td> <em>Caption:</em> <p>Uses asymmetrical character combinations with regex to replace bold, italic, color, etc. with<br>the right HTML tag. The assignment says that the USER must not put<br>in HTML but the output should be HTML so that rule has been<br>followed. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T18.23.44image.png.webp?alt=media&token=c4d24ef5-c953-4db9-b3fc-923cce47a905"/></td></tr>
<tr><td> <em>Caption:</em> <p>The reference to messageFormat in the sendMessage code which lets the altered message<br>be sent out. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show source message and the result output in the terminal (note, you won't actually see the styles until Milestone3)</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-11T18.37.56image.png.webp?alt=media&token=e0a4ca45-71a1-4665-9365-371dc5d0b782"/></td></tr>
<tr><td> <em>Caption:</em> <p>Bold, Underline, Italic, Color and a mix of all of them are all<br>shown and displayed with their respective commands. The command is input around the<br>message and the output returns the same message with the appropriate HTML tag.<br><br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Explain how you got each style applied</td></tr>
<tr><td> <em>Response:</em> <ol><br><li>Bold was applied asymmetrically with $* <em>$ brackets. The brackets get replaced<br>with&nbsp;&lt;b&gt;, &lt;/b&gt;.<div><br></div><div>2. Italics were applied asymmetrically with $/ /$ brackets.&nbsp; The brackets get<br>replaced with&nbsp;&lt;i&gt;, &lt;/i&gt;.</div><div><br></div><div>3. Underline was applied asymmetrically with $_ _$ brackets. The brackets<br>get replaced with&nbsp;&lt;u&gt;, &lt;/u&gt;.</div><div><br></div><div>4. Colors were in an RGB (Red, Green, Blue) format<br>and were applied asymmetrically with $r r$, $g g$, $b b$, respectively. The<br>brackets get replaced with (&lt;font color= red&gt;, &lt;/font&gt;), (&lt;font color= green&gt;, &lt;/font&gt;), and<br>(&lt;font color= blue&gt;, &lt;/font&gt;), respectively.&nbsp;</div><div><br></div><div>All of these input brackets used replaceAll and regex,<br>example (with bold):&nbsp;<br><div>newMessage = newMessage.replaceAll(&quot;\$\</em>&quot;, &quot;&lt;b&gt;&quot;);</div><div><span style="white-space: normal;">newMessage = newMessage.replaceAll(&quot;\*\$&quot;, &quot;&lt;/b&gt;&quot;);&nbsp;</span></div></div><div><span style="white-space: normal;"><br></span></div><div>These<br>changes are sent through this line inside of the sendMessage bracket, which passes<br>the new messageFormat into the message to be sent:&nbsp;</div><div>message = messageFormat(message);<br></div><br></li><br></ol><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/PeterRedfern/par36-it114-005/pull/7">https://github.com/PeterRedfern/par36-it114-005/pull/7</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-chatroom-milestone-2/grade/par36" target="_blank">Grading</a></td></tr></table>