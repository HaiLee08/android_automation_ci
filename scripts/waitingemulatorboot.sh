EMU_BOOTED=0
n=0
first_launcher=1
echo 1 > /tmp/failed

while [[ $EMU_BOOTED == 0 ]]; do
    echo "Test for current focus"
    # $ANDROID_HOME/platform-tools/adb shell dumpsys window
    CURRENT_FOCUS=$($ANDROID_HOME/platform-tools/adb shell dumpsys window 2>/dev/null | grep -i mCurrentFocus)
    echo "Current focus: ${CURRENT_FOCUS}"

    case $CURRENT_FOCUS in
        *"Launcher"*)
            if [[ $first_launcher == 1 ]]; then
                echo "Launcher seems to be ready, wait 10 sec for another popup..."
                sleep 10
                first_launcher=0
            else
                echo "Launcher is ready, Android boot completed"
                EMU_BOOTED=1
                rm /tmp/failed
            fi
            ;;
        *"Not Responding: com.android.systemui"*)
            echo "Dismiss System UI isn't responding alert"
            adb shell input keyevent KEYCODE_ENTER
            adb shell input keyevent KEYCODE_DPAD_DOWN
            adb shell input keyevent KEYCODE_ENTER
            first_launcher=1
            ;;
        *"Not Responding: com.google.android.gms"*)
            echo "Dismiss GMS isn't responding alert"
            adb shell input keyevent KEYCODE_ENTER
            first_launcher=1
            ;;
        *"Not Responding: system"*)
            echo "Dismiss Process system isn't responding alert"
            adb shell input keyevent KEYCODE_ENTER
            first_launcher=1
            ;;
        *)
            n=$((n + 1))
            echo "Waiting Android to boot 5 sec ($n)..."
            sleep 5
            exit 1
            ;;
    esac
done

echo "Android Emulator started."
