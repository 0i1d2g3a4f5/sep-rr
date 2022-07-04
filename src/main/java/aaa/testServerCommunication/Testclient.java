package aaa.testServerCommunication;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Testclient {
    Socket socket = new Socket("localhost",12345);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();

    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
    public static void main(String[] args) throws IOException {
        Testclient testclient = new Testclient();
        Thread thread = new Thread(testclient.listen);
        thread.isDaemon();
        thread.start();
        System.out.println("listener started");

        testclient.sendText("{\n" +
                "  \"gameMap\": [\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"StartPoint\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Antenna\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"orientations\": [\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"StartPoint\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"StartPoint\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"orientations\": [\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"StartPoint\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"StartPoint\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"StartPoint\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"speed\": 1,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"orientations\": [\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"orientations\": [\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"Start A\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"Start A\",\n" +
                "          \"speed\": 1,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"EnergySpace\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\",\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"EnergySpace\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"Laser\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"top\"\n" +
                "          ],\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"RestartPoint\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"EnergySpace\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"Laser\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"left\"\n" +
                "          ],\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"Laser\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"right\"\n" +
                "          ],\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"EnergySpace\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"top\"\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"Laser\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\"\n" +
                "          ],\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Wall\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"orientations\": [\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"EnergySpace\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"right\",\n" +
                "            \"left\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\",\n" +
                "            \"left\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"top\",\n" +
                "            \"bottom\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"EnergySpace\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"ConveyorBelt\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"speed\": 2,\n" +
                "          \"orientations\": [\n" +
                "            \"left\",\n" +
                "            \"right\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"CheckPoint\",\n" +
                "          \"isOnBoard\": \"5B\",\n" +
                "          \"count\": 1\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ],\n" +
                "      [\n" +
                "        {\n" +
                "          \"type\": \"Empty\",\n" +
                "          \"isOnBoard\": \"5B\"\n" +
                "        }\n" +
                "      ]\n" +
                "    ]\n" +
                "  ]\n" +
                "}");



    }

    void sendText(String input) throws IOException {
        input.trim();
        input += "\n";
        char[] arr = input.toCharArray();
        for (char c:arr) {
            dataOutputStream.writeInt((int)c);
        }

        dataOutputStream.flush();
    }

    Runnable listen = new Runnable() {
        @Override
        public void run() {
            try {
                while(true){
                    TimeUnit.MILLISECONDS.sleep(100);
                    String text = "";
                    while(dataInputStream.available()>0){
                        text+= (char)dataInputStream.readInt();
                    }
                    if(text !="")
                    System.out.println(text);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    };


    public Testclient() throws IOException {
    }
}
