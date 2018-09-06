def setup():
    global sun, earth
    size(400, 400, P3D)
    sphereDetail(16, 8)
    sun = earth = 0
    
def draw():
    background(220)
    translate(width/2, height/2, 0)
    global sun, earth 
    
    pushMatrix()
    rotateY(radians(sun))
    fill('#ffff00'); sphere(60)
    sun += 0.2
    popMatrix()
    
    #rotateY(radians(earth))
    translate(100, 0, 0)
    rotateY(radians(100))
    fill('#0000ff'); sphere(20)
    earth += 1
    
