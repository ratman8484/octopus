import pyautogui
import time
from numpy import random

boxCount = 9

boxW = 24

startX = 672+15
endX = startX + boxCount*boxW
startY = 307+15
endY = startY + boxCount*boxW

def goToRand():
    boxX = random.randint(0, boxCount)
    boxY = random.randint(0, boxCount)
    
    pyautogui.moveTo(startX + boxW * boxX, startY + boxW * boxY)    




while 1 == 1:
    #time.sleep(1)
    goToRand()
    pyautogui.click()
    
    x, y = pyautogui.position()  # get mouse position
    pixel_color = pyautogui.screenshot().getpixel((x, y))  # get color

    if pixel_color[0] < 20 and pixel_color[1] < 20 and pixel_color[2] < 20:
        pyautogui.moveTo(776, 263)
        pyautogui.click()