package com.kk.aoc.dd.model.address;

import java.util.ArrayList;
import java.util.List;

public class AddressTree {
    private final Position root = new Position(null, '@');

    public Position getRoot() {
        return root;
    }

    public void extendAddress(char... chars) {
        root.extendAddress(chars);
    }

    public List<String> collectAddresses() {
        List<String> addresses = new ArrayList<>();
        collectAddresses(root, addresses, "");
        return addresses;
    }

    private void collectAddresses(Position pos, List<String> addresses, String addressBuilder) {
        String newAddressBuilder = addressBuilder;
        if (pos != root) {
            newAddressBuilder = addressBuilder + pos.getCharAtPosition();
        }
        if (pos.getNextPositions().isEmpty()) {
            addressBuilder += pos.getCharAtPosition();
            addresses.add(addressBuilder);
        } else {
            String finalNewAddressBuilder = newAddressBuilder;
            pos.getNextPositions().forEach(position -> collectAddresses(position, addresses, finalNewAddressBuilder));
        }
    }
}
