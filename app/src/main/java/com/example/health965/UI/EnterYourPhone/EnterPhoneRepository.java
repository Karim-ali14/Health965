package com.example.health965.UI.EnterYourPhone;

public class EnterPhoneRepository {
    public static EnterPhoneRepository ePRepository;

    public static EnterPhoneRepository getInstance() {
        if (ePRepository == null)
            ePRepository = new EnterPhoneRepository();
        return ePRepository;
    }

}
