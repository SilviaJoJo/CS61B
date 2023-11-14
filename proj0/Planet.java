public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dX = p.xxPos - xxPos;
        double dY = p.yyPos - yyPos;
        double distance = Math.sqrt(dX * dX + dY * dY);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67E-11;
        double distance = this.calcDistance(p);
        double force = (G * mass * p.mass) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double force = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        double distanceX = p.xxPos - xxPos;
        double forceX = force * distanceX / distance;
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double force = this.calcForceExertedBy(p);
        double distance = this.calcDistance(p);
        double distanceY = p.yyPos - yyPos;
        double forceY = force * distanceY / distance;
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double totalForceX = 0;
        for (int i = 0; i < ps.length; i++) {
            if (this.equals(ps[i])) {
                continue;
            }
            double forceX = this.calcForceExertedByX(ps[i]);
            totalForceX = totalForceX + forceX;
        }
        return totalForceX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double totalForceY = 0;
        for (int i = 0; i < ps.length; i++) {
            if (this.equals(ps[i])) {
                continue;
            }
            double forceY = this.calcForceExertedByY(ps[i]);
            totalForceY = totalForceY + forceY;
        }
        return totalForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        /* Stamps three copies of advice.png in a triangular pattern. */
        StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
    }
}