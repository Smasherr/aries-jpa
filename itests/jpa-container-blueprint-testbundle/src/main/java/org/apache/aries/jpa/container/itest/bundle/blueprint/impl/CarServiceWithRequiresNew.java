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

public class CarServiceWithRequiresNew extends AbstractCarServiceImpl
{

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
        this.addCar(c);
        return Arrays.asList(this.getCar("TR123"));
    }

    @Override
    public void updateCar(Car car) {
    }

    @Override
    public void deleteCar(String id) {
    }

}



