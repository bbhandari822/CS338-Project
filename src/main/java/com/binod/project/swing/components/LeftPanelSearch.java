package com.binod.project.swing.components;

import com.binod.project.swing.domain.Member;
import com.binod.project.swing.domain.MemberCustomList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class LeftPanelSearch extends Component {

    private static JFrame frame = new JFrame();
    private static JLayeredPane lpane = new JLayeredPane();
    private static JPanel memberListPanel = new JPanel();
    private static JPanel channelListPanel = new JPanel();
    private static JTextArea channelAreaBox;
    private static JTextField channelTextField;
    private static JFrame showErrorMessage;


    private MemberCustomList<Member> listModel;
    private JList<Member> listPerson = new JList<>();
    private java.util.List<Member> members = new ArrayList<>();

    public LeftPanelSearch() {
        memberListPanel().setBorder(BorderFactory.createLineBorder(Color.GRAY));
        memberListPanel.setBounds(5, 20, 300, 350);
        memberListPanel.setBorder(new TitledBorder(new EtchedBorder(), "Members"));
        TitledBorder titledBorderMember = (TitledBorder)memberListPanel.getBorder();
        titledBorderMember.setTitleColor(Color.WHITE);
        memberListPanel.setBackground(Color.decode("#601760"));
        channelListPanel().setBorder(BorderFactory.createLineBorder(Color.GRAY));
        channelListPanel.setBounds(5, 400, 300, 345);
        channelListPanel.setBorder(new TitledBorder(new EtchedBorder(), "Channels"));
        TitledBorder titledBorderChannel = (TitledBorder)channelListPanel.getBorder();
        titledBorderChannel.setTitleColor(Color.WHITE);
        channelListPanel.setBackground(Color.decode("#601760"));

    }


    private static JPanel channelListPanel(){

        channelListPanel = new JPanel();
        channelAreaBox = new JTextArea(17, 22);
        channelAreaBox.setEditable(false);
        channelAreaBox.setText("Channel 1\n");
        JScrollPane jScrollPane = new JScrollPane(channelAreaBox);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        channelListPanel.add(jScrollPane, gridBagConstraints);

        channelTextField = new JTextField(15);
        channelTextField.addActionListener(e -> {
            String text = channelTextField.getText();
            getTextFromChannelTextField(text);
        });
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        channelListPanel.add(channelTextField, gridBagConstraints);
        JButton addChannel = new JButton("Add");
        addChannel.addActionListener(e -> {
            String text = channelTextField.getText();
            getTextFromChannelTextField(text);
        });
        channelListPanel.add(addChannel);
        return channelListPanel;
    }

    private static void getTextFromChannelTextField(String text) {
        if(text.equals("")){
            JOptionPane.showMessageDialog(showErrorMessage,
                    "Channel name cannot be empty");
        }else {
            channelAreaBox.append(text + "\n");
            channelTextField.selectAll();
            channelAreaBox.setCaretPosition(channelAreaBox.getDocument().getLength());
            channelTextField.setText("");
        }
    }

    private JPanel memberListPanel(){

        memberListPanel = new JPanel();

        listModel = new MemberCustomList<>(members);
        listPerson.setModel(listModel);

        JScrollPane jScrollPane = new JScrollPane(listPerson);
        jScrollPane.setPreferredSize(new Dimension(265, 290));
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        memberListPanel.add(jScrollPane, gridBagConstraints);
        JButton addMember = new JButton("Add");
        JButton searchMemeber = new JButton("Search");

        addMember.addActionListener(evt -> addMemberNames());

        searchMemeber.addActionListener(e -> searchPersons());
        memberListPanel.add(addMember);
        memberListPanel.add(searchMemeber);
        return memberListPanel;
    }

    private void addMemberNames(){
        String memberName = JOptionPane.showInputDialog("Enter member name");
        if (memberName == null) {
            JOptionPane.showMessageDialog(showErrorMessage,
                    "Member name cannot be empty");
        }else{
            if(memberName.startsWith(" ") || memberName.startsWith("@")){
                JOptionPane.showMessageDialog(showErrorMessage,
                        "Member name cannot start with spaces or @");
            }else{
                listModel.addElement(new Member(memberName));

            }
        }
    }

    private void searchPersons() {
        String memberName = JOptionPane.showInputDialog("Enter member name to search");
        if (memberName == null) {
            return;
        }
        Collections.sort(members);
        int foundIndex = Collections.binarySearch(members, new Member(memberName));
        if (foundIndex >= 0) {
            listPerson.setSelectedIndex(foundIndex);
        } else {
            JOptionPane.showMessageDialog(showErrorMessage,"Could not find the person " + memberName);
        }
    }

    public JLayeredPane returnLeftPanel(JFrame frame){
        lpane.setPreferredSize(new Dimension(320,320));

        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 300, 300);
        lpane.setSize(100,100);
        lpane.add(memberListPanel, 0, 0);
        lpane.add(channelListPanel, 1, 0);
        return lpane;
    }

}
