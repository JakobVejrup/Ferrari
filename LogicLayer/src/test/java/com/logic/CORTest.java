package com.logic;

import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.mockups.MockupEmployee;
import com.logic.mockups.MockupEmployeeData;
import com.logic.services.customer.CustomerService;
import com.logic.services.employee.EmployeeService;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//anders
//all tests ran true
public class CORTest extends TestCase
{
    public CORTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( CORTest.class);
    }
    //all tests are only testing the id of the mockup employee, it's only to show that Cor works
    //the services have dependency injections, so it's easy to inject a mockup class.
    //servicesingleton must have its services set to use the mockup ones, singleton no problemo
    //tests if no service with return nothing
    public void testServiceMissing() {
        ServiceSingleton.getInstance().setServices(null);
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Create, (result) ->
                assertEquals(null, result)
        ));
    }
    //tests Create
    public void testServiceCreate() {
        ServiceSingleton.getInstance().setServices(new SimpleHolder(new EmployeeService(new MockupEmployeeData(), null)));
        Object result = ServiceSingleton.getInstance().queryNoThread(new Request(ServiceType.Employee, CRUDType.Create, new MockupEmployee()));
        assertEquals(new MockupEmployee(1), result);
    }
    //tests Read
    public void testServiceRead() {
        ServiceSingleton.getInstance().setServices(new SimpleHolder(new EmployeeService(new MockupEmployeeData(), null)));
        Object result = ServiceSingleton.getInstance().queryNoThread(new Request(ServiceType.Employee, CRUDType.Read));
        assertEquals(new MockupEmployee(0), result);
    }
    //tests ReadAll
    public void testServiceReadAll() {
        ServiceSingleton.getInstance().setServices(new SimpleHolder(new EmployeeService(new MockupEmployeeData(), null)));
        Object result = ServiceSingleton.getInstance().queryNoThread(new Request(ServiceType.Employee, CRUDType.ReadAll));
        MockupEmployee[] array = new MockupEmployee[] {new MockupEmployee(1), new MockupEmployee(2)};
        assertEquals(array[0], ((MockupEmployee[])result)[0]);
        assertEquals(array[1], ((MockupEmployee[])result)[1]);
    }
    //tests an updated value
    public void testServiceUpdate() {
        ServiceSingleton.getInstance().setServices(new SimpleHolder(new EmployeeService(new MockupEmployeeData(), null)));
        Object result = ServiceSingleton.getInstance().queryNoThread(new Request(ServiceType.Employee, CRUDType.Update, 5));
        assertEquals(new MockupEmployee(5), result);
    }
    //tests delete
    public void testServiceDelete() {
        ServiceSingleton.getInstance().setServices(new SimpleHolder(new EmployeeService(new MockupEmployeeData(), null)));
        Object result = ServiceSingleton.getInstance().queryNoThread(new Request(ServiceType.Employee, CRUDType.Delete, new MockupEmployee(5)));
        assertEquals(true, result);
    }
    //tests priorityplacements
    public void testHandlerPriority() {
        HandlerObject cs = new CustomerService(new MockupEmployeeData());
        HandlerObject es = new EmployeeService(new MockupEmployeeData(), null);
        ServiceSingleton.getInstance().setServices(new SimpleHolder(cs, es));
        assertEquals(cs, ServiceSingleton.getInstance().getServices().getRoot());
        ServiceSingleton.getInstance().queryNoThread(new Request(ServiceType.Employee, CRUDType.Delete, new MockupEmployee(5)));
        assertEquals(es, ServiceSingleton.getInstance().getServices().getRoot());
    }


}
