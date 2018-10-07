/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIESOR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.aries.jpa.container.itest.bundle.blueprint.impl2;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.aries.jpa.container.itest.entities.Car;
import org.apache.aries.jpa.container.itest.entities.CarService;

public class CarServiceUsingOtherService implements CarService {
    
    protected CarService otherCarService;
    
    @PersistenceContext(unitName = "xa-test-unit")
    protected EntityManager em;

    @Override
    public Car getCar(String id) {
        return otherCarService.getCar(id);
    }

    @Override
    public void addCar(Car car) {
        otherCarService.addCar(car);
    }

    @Override
    public Collection<Car> getCars() {
        return otherCarService.getCars();
    }

    @Override
    public void updateCar(Car car) {
        otherCarService.updateCar(car);
    }

    @Override
    public void deleteCar(String id) {
        otherCarService.deleteCar(id);
    }

}
