def setup():
    size(400, 400)
    
def draw():
    stroke('#000000'); noFill()
    background(200)
    rect(100, 350, 200, 50)
    cx1, cy1 = 200, 375
    ellipse(cx1, cy1, 5, 5)
    
    translate(cx1, cy1)
    rotate(radians(frameCount))
    rect(-20, -180, 40, 200)
    cx2, cy2 = 0, -160
    stroke("#ff0000"); ellipse(cx2, cy2, 5, 5)
    
    translate(cx2, cy2)
    rotate(radians(frameCount))
    stroke('#0000ff'); rect(-10, -90, 20, 100)
    
    cx3, cy3 = 0, -80
    stroke("#00ff00"); ellipse(cx3, cy3, 5, 5)
    translate(cx3, cy3)
    rotate(radians(frameCount))
    rect(-5, -45, 10, 50)
    
    
    
    
    
    
    
