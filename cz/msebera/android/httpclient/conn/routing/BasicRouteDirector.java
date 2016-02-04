package cz.msebera.android.httpclient.conn.routing;

import cz.msebera.android.httpclient.util.Args;

public class BasicRouteDirector implements HttpRouteDirector {
    public int m11682a(RouteInfo routeInfo, RouteInfo routeInfo2) {
        Args.m12722a((Object) routeInfo, "Planned route");
        if (routeInfo2 == null || routeInfo2.m11688c() < 1) {
            return m11681a(routeInfo);
        }
        if (routeInfo.m11688c() > 1) {
            return m11684c(routeInfo, routeInfo2);
        }
        return m11683b(routeInfo, routeInfo2);
    }

    protected int m11681a(RouteInfo routeInfo) {
        return routeInfo.m11688c() > 1 ? 2 : 1;
    }

    protected int m11683b(RouteInfo routeInfo, RouteInfo routeInfo2) {
        if (routeInfo2.m11688c() > 1 || !routeInfo.m11685a().equals(routeInfo2.m11685a()) || routeInfo.m11692g() != routeInfo2.m11692g()) {
            return -1;
        }
        if (routeInfo.m11687b() == null || routeInfo.m11687b().equals(routeInfo2.m11687b())) {
            return 0;
        }
        return -1;
    }

    protected int m11684c(RouteInfo routeInfo, RouteInfo routeInfo2) {
        if (routeInfo2.m11688c() <= 1 || !routeInfo.m11685a().equals(routeInfo2.m11685a())) {
            return -1;
        }
        int c = routeInfo.m11688c();
        int c2 = routeInfo2.m11688c();
        if (c < c2) {
            return -1;
        }
        for (int i = 0; i < c2 - 1; i++) {
            if (!routeInfo.m11686a(i).equals(routeInfo2.m11686a(i))) {
                return -1;
            }
        }
        if (c > c2) {
            return 4;
        }
        if (routeInfo2.m11690e() && !routeInfo.m11690e()) {
            return -1;
        }
        if (routeInfo2.m11691f() && !routeInfo.m11691f()) {
            return -1;
        }
        if (routeInfo.m11690e() && !routeInfo2.m11690e()) {
            return 3;
        }
        if (routeInfo.m11691f() && !routeInfo2.m11691f()) {
            return 5;
        }
        if (routeInfo.m11692g() == routeInfo2.m11692g()) {
            return 0;
        }
        return -1;
    }
}
