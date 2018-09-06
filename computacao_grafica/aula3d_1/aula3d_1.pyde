def axis():
    stroke("#ff0000");
    line(-10, 0, 0, 300, 0, 0);
    stroke("#00ff00");
    line(0, -10, 0, 0, 300, 0);
    stroke("#0000ff");
    line(0, 0, -10, 0, 0, 300);    

def setup():
    global a, b, s
    size(400, 400, P3D)
    a = b = 0
    s = createShape()
    s.beginShape(TRIANGLES) # POINTS LINES LINE_LOOP
    s.vertex(0, 0, 0); s.vertex(50, 0, 0); s.vertex(0, 150, 0)
    s.endShape()

def draw():
    background(220)
    translate(width/2, height/2, 0)
    axis()
    
    global a, b
    rotateX(radians(a))
    rotateY(radians(b))
    stroke(0); shape(s)
    a += mouseY - pmouseY
    b += mouseX - pmouseX
