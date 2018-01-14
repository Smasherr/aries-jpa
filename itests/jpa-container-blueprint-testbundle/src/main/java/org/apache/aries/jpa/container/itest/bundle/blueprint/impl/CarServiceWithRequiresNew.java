/*
 * CarServiceWithRequiresNew.java
 *
 * created at 09.01.2018 by esterman d.estermann@seeburger.de
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package org.apache.aries.jpa.container.itest.bundle.blueprint.impl;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.aries.jpa.container.itest.entities.Car;
import org.apache.aries.jpa.container.itest.entities.CarService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class CarServiceWithRequiresNew extends AbstractCarServiceImpl
{
	BundleContext bundleContext;

    @Override
    @Transactional(TxType.REQUIRES_NEW)
    public Car getCar(String id) {
        return em.find(Car.class, id);
    }

    @Override
    @Transactional(TxType.REQUIRES_NEW)
    public void addCar(Car car) {
        em.persist(car);
        em.flush();
    }

    @Override
    @Transactional(TxType.NEVER)
    public Collection<Car> getCars()
    {
        Car c = new Car();
        c.setNumberPlate("TR123");
        ServiceReference<CarService> ref = null;
		try {
        		ref = getService();
			CarService carService = bundleContext.getService(ref);
			carService.addCar(c);
			return Arrays.asList(this.getCar("TR123"));
		} finally {
			if (ref != null) {
				bundleContext.ungetService(ref);
			}
		}
    }

	private ServiceReference<CarService> getService() {
		try {
			Collection<ServiceReference<CarService>> refs = bundleContext.getServiceReferences(CarService.class,
					"(type=rn)");
			return refs.iterator().next();
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
    public void updateCar(Car car) {
    }

    @Override
    public void deleteCar(String id) {
    }

    public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
    
}



