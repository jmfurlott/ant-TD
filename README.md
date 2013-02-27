1. download libgdx
2. clone the repository
3. import into eclipse


-never push the gen/ and bin/ folders in the android subfolder though all
 changes will be handled from the ant-td/ folder

-all assets need to go into the android folder and they will be referenced 
 from all the other platforms (so need to only save the assets once)
	-assets are things like images, sounds, music, etc.

-each platform has their own config file but all actual code will go in
 the top java class
	-changes within the ant-td/ folder will be reflected across the 
	 desktop app and the android app
