package io.github.Nick3306.Duel;

import org.bukkit.Location;
import org.bukkit.World;

public class Arena 
{
	String name;
	double x, y, z;
	double x2, y2, z2;
	Location loc2;
	World world;
	String getName()
	{
		return name;
	}
	Arena(World world, String name, double x, double y, double z, double x2, double y2, double z2)
	{
		this.world = world;
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}
	World getWorld()
	{
		return world;
	}
	double getX()
	{
		return x;
	}
	double getY()
	{
		return y;
	}
	double getZ()
	{
		return z;
	}
	double getX2()
	{
		return x2;
	}
	double getY2()
	{
		return y2;
	}
	double getZ2()
	{
		return z2;
	}
}
