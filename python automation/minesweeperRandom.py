import pyautogui
import time
from numpy import random

#this minesweeper algorithm clicks a random location
#resetting the game if it loses

#flick the mouse to the top left of your monitor to kill the program

boxCount = 9 ##width

boxW = 24#width of the box in px

startX = 672+15#top left of the game space + offset
endX = startX + boxCount*boxW#bottom right
startY = 307+15#top left of the game space + offset
endY = startY + boxCount*boxW #bottom right

def goToRand():
    #moves the mouse to a random position
    boxX = random.randint(0, boxCount)
    boxY = random.randint(0, boxCount)
    
    pyautogui.moveTo(startX + boxW * boxX, startY + boxW * boxY)    



#infinite loop
while 1 == 1:
    #time.sleep(1) #change this line if you want to add a delay
    
    #first it goes to a random position
    goToRand()
    pyautogui.click()
    
    
    x, y = pyautogui.position()  #gets the mouse position
    pixel_color = pyautogui.screenshot().getpixel((x, y))  #get color of the space it's on

    #if it revealed a bomb, it restarts
    if pixel_color[0] < 20 and pixel_color[1] < 20 and pixel_color[2] < 20:
        pyautogui.moveTo(776, 263)
        pyautogui.click()