import math
angle = x = y = velocity = 0

def setup():
    global angle, x, y, s, velocity, asteroid
    angle = velocity = 0
    x = y = 150
    size(300,300)
    s = createShape()
    s.beginShape(QUADS)
    s.vertex(0, -10)
    s.vertex(10, 10)
    s.vertex(0, 5)
    s.vertex(-10, 10)    
    s.vertex(0, -10)
    s.endShape();
    global a1, a2
    a1 = 30; a2 = 120
    asteroid = createShape(ELLIPSE, 0, 0, 40, 70)

def makeAsteroid():
    global ast, x, y
    ast = createShape()
    ast.beginShape(QUADS)
    ast.endShape();
    ast.translate(x,y)
    shape(ast)
    print('done')
    
def circle(x, y, r):
    noFill(); ellipse(x, y, r, r); fill(255)

def draw():    
    global angle, velocity, x, y, a1, a2, s
    background(200)
    pushMatrix()
    translate(x, y)
    rotate(radians(angle))
    shape(s)
    popMatrix()
    vx = velocity * cos(radians(angle + 90))
    vy = velocity * sin(radians(angle + 90))
    x += vx
    y += vy
    if x < 0: x += width
    if y < 0: y += height
    if x > width: x -= width
    if y > height: y -= height
    background(200)
    pushMatrix(); translate(x, y); rotate(radians(angle))
    circle(0, 0, 30) 
    shape(s); popMatrix()
    
    pushMatrix(); translate(30, 60); rotate(radians(a1));
    circle(0, 0, 70)
    shape(asteroid); popMatrix()
    a1 += 4
    
    pushMatrix(); translate(230, 150); rotate(radians(a2));
    circle(0, 0, 70) 
    shape(asteroid); popMatrix()
    a2 += 2.5
    
    if collide(x, y, 30/2, 30, 60, 70/2):
        v = PVector(vx, vy)
        n = PVector(x-30, y-60)
        n.normalize()
        v = reflect(v, n)
    elif collide(x, y, 30/2, 230, 150, 70/2): print("collide second")
    else: print("")
    
def collide(x1, y1, r1, x2, y2, r2):
    return math.sqrt((x1-x2)**2 + (y1-y2)**2) <= r1 + r2

def reflect(v, n):
    v = PVector(vx, vy); n = PVector(nx, ny)
    return v - 2 * (v * n) * n
    
def keyPressed():
    global x, y, angle, velocity
    if (key == CODED):        
        if (keyCode == UP and (velocity > 0.3 or velocity == 0)):    velocity -= 0.5 
        if (keyCode == DOWN and (velocity < 0.3 or velocity == 0)):  velocity += 0.5
        if (keyCode == LEFT):  angle -= 5
        if (keyCode == RIGHT): angle += 5

    
