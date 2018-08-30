angle = x = y = speed = 0

def setup():
    global angle, x, y, s, speed
    angle = speed = 0
    x = y = 150
    size(200,200)
    s = createShape()
    s.beginShape(QUADS)
    s.vertex(0, -10)
    s.vertex(10, 10)
    s.vertex(0, 5)
    s.vertex(-10, 10)    
    s.vertex(0, -10)
    s.endShape();

def makeAsteroid():
    global ast, x, y
    ast = createShape()
    ast.beginShape(QUADS)
    ast.endShape();
    ast.translate(x,y)
    shape(ast)
    print('done')

def draw():    
    global angle, s, x, y, speed
    background(200)
    pushMatrix()
    translate(x, y)
    rotate(radians(angle))
    shape(s)
    popMatrix()
    x += speed * cos(radians(angle + 90))
    y += speed * sin(radians(angle + 90))
    if x < 0: x += width
    if y < 0: y += height
    if x > width: x -= width
    if y > height: y -= height
    
def keyPressed():
    global x, y, angle, speed
    if (key == CODED):        
        if (keyCode == UP):    speed -= 0.3#; makeAsteroid(); 
        if (keyCode == DOWN):  speed += 0.3==
        if (keyCode == LEFT):  angle -= 5 #x -= 5
        if (keyCode == RIGHT): angle += 5 #x += 5

    
